package middle.service.demo;

import middle.service.demo.Constant.inventory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class generateToken {
    @Test
    public void tokenGenerate(){
        // 1. Current DateTime
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");*/
        String currentDateTime = LocalDateTime.now().format(inventory.dateTimeFormatter);
        //String currentDateTime = "2025-04-26T18:08:47.609402300";
        String lastPart = null;

        inventory.getToken();

        // 2. First Part: Digest
        String hmacString = inventory.hmackey + ":" + currentDateTime;
        String firstPart = DigestUtils.sha512Hex(hmacString);

        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, inventory.aeskey);
            byte[] encrypted = cipher.doFinal(currentDateTime.getBytes());
            lastPart = Base64.encodeBase64String(encrypted);//Base64.encodeBase64String
            String bearerToken = firstPart + "." + lastPart;
            System.out.println(currentDateTime);
            System.out.println("Bearer Token:\n" + bearerToken);
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
               IllegalBlockSizeException | BadPaddingException exception){
            System.out.println("Error: " + exception.getMessage());
        }
    }
}

