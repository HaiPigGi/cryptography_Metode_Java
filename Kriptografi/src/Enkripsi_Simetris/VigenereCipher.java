package Enkripsi_Simetris;

import java.util.Scanner;
public class VigenereCipher {
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int encryptedChar = (plainChar + keyChar - 2 * base) % 26 + base;
                ciphertext.append((char) encryptedChar);
            } else {
                ciphertext.append(plainChar); // Non-alphabetic characters are not encrypted
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(cipherChar)) {
                char base = Character.isUpperCase(cipherChar) ? 'A' : 'a';
                int decryptedChar = (cipherChar - keyChar + 26) % 26 + base;
                plaintext.append((char) decryptedChar);
            } else {
                plaintext.append(cipherChar); // Non-alphabetic characters are not decrypted
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scan= new Scanner (System.in);
        System.out.println("VigenereCipher");
        System.out.println("=========================");
        
        
        System.out.print("Masukkan Plain Text : ");
        String plaintext = scan.next();
        String upPlain=  plaintext.toUpperCase();
        System.out.print("Masukkan Key : ");
        String key = scan.next();
        String upKey=key.toUpperCase();

        String encryptedText = encrypt(upPlain, upKey);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, upKey);
        System.out.println("Decrypted: " + decryptedText);
    }
}
