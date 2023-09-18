/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enkripsi_Simetris;

public class PolyalphabeticCipher {

    // Fungsi untuk mengenkripsi teks
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toLowerCase();
        key = key.toLowerCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % key.length());
            
            if (Character.isLetter(plainChar)) {
                int shift = keyChar - 'a';
                char encryptedChar = (char) ('a' + (plainChar - 'a' + shift) % 26);
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(plainChar);
            }
        }

        return ciphertext.toString();
    }

    // Fungsi untuk mendekripsi teks
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toLowerCase();
        key = key.toLowerCase();

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % key.length());
            
            if (Character.isLetter(cipherChar)) {
                int shift = keyChar - 'a';
                char decryptedChar = (char) ('a' + (cipherChar - 'a' - shift + 26) % 26);
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(cipherChar);
            }
        } 

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "kami mahasiswa universitas sanata dharma";
        String key = "keykey keykey"; // Ganti dengan kunci yang sesuai

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
