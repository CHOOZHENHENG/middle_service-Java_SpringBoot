package middle.service.demo.Constant;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class inventory {
    public static SecretKeySpec hmackey, aeskey;

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static void getToken(){
        try{
            hmackey = new SecretKeySpec(Files.readAllBytes(Paths.get("src/mnt/secrets-store/hmac.txt")), "");
            aeskey = new SecretKeySpec(Files.readAllBytes(Paths.get("src/mnt/secrets-store/aes.txt")), "AES");
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
