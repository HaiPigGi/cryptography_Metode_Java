package Enkripsi_Simetris;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
    public static String encryptDES (String plainText, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec (key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretkey = keyFactory.generateSecret(desKeySpec);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String decryptDES (String cipherText, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String (decryptedBytes);
    }
    
    public static void main(String[] args) {
        try {
            String plainText = "Hello, DES!";
            String key = "ThisIsAKey"; // 8 Bytes For DES
            
            String encryptedText = encryptDES(plainText,key);
            System.out.println("Encrypted : "+encryptedText);
            
            String decryptedText = decryptDES (encryptedText,key);
            System.out.println("Decrypted : "+decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
