
package Enkripsi_Simetris;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    // Ini adalah Algoritma AES dengan Metode ECB (Electronic Codebook)
    
    public static String encryptedAES (String plainText, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher=  Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
        
    }
    
    public static String decryptedAES (String cipherText, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(),"AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes= cipher.doFinal(encryptedBytes);
        return new String (decryptedBytes);
    }
    
    public static void main(String[] args) {
        try {
            String plainText = "Hello, AES!";
            String key = "ThisIsASecretKey";
            
            String encryptedText = encryptedAES (plainText,key);
            System.out.println("Encrypted : "+encryptedText);
            
            String decryptedText = decryptedAES (encryptedText,key);
            System.out.println("Decrypted : "+decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
}
