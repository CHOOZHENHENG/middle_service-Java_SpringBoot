package middle.service.demo.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import middle.service.demo.Constant.inventory;
import middle.service.demo.Security.token;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Component
public class SpringInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        //Variables
            //for aes key
            Cipher cipher;
            byte[] decryptedAesByte;
            String lastPart = "";

            //for duration
            LocalDateTime aesLocalDateTime, nowLocalDateTime;
            Duration duration;
            long secsDifference;

            //for generate hmac
            String hmacString = "", encryptedHmacString = "";

        //Get bearer token from request header
            token.bearerToken = request.getHeader("Authorization");

            if(token.bearerToken == null)
                throw HttpClientErrorException.create(HttpStatus.UNAUTHORIZED, "",
                        null, null, null);
            else token.bearerToken = token.bearerToken.substring(7);

        //Extract by splitting
            token.parts = token.bearerToken.split("\\.");

        inventory.getToken();

        //Decrypt last part by aes key
            try{
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, inventory.aeskey);
                decryptedAesByte = cipher.doFinal(Base64.getDecoder().decode(token.parts[1]));
                lastPart = new String(decryptedAesByte);
            }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                    IllegalBlockSizeException | BadPaddingException ex){
                return false;
            }

        //Get duration from last part
            aesLocalDateTime = LocalDateTime.parse(lastPart);
            nowLocalDateTime = LocalDateTime.parse(LocalDateTime.now().toString());
            duration = Duration.between(aesLocalDateTime, nowLocalDateTime);
            secsDifference = Math.abs(duration.toSeconds());

        //Check if exceeds 1 hour
            if(secsDifference <= 3600){
                //Generate HMAC to compare
                hmacString = inventory.hmackey + ":" + lastPart;
                encryptedHmacString = DigestUtils.sha512Hex(hmacString);

                //Compare HMAC part
                if(Objects.equals(token.parts[0], encryptedHmacString)){
                   /* System.out.println(token.parts[0]);
                    System.out.println(encryptedHmacString);
                    System.out.println("Valid token");*/
                    System.out.println("Request URL: " + request.getRequestURL());
                    return true;
                }
                else throw HttpClientErrorException.create(HttpStatus.UNAUTHORIZED, "", null, null, null);
            }
            else throw HttpClientErrorException.create(HttpStatus.UNAUTHORIZED, "", null, null, null);
    }
}
