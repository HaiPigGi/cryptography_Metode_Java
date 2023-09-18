/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enkripsi_Simetris;

public class MonoalphabeticCipher {
    private static final String PLAIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String CIPHER_ALPHABET = "ghijklmnopqrstuvwxyzabcdef"; // Abjad acak untuk mengenkripsi

    // Fungsi untuk mengenkripsi teks
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = PLAIN_ALPHABET.indexOf(c);
                if (index != -1) {
                    char encryptedChar = CIPHER_ALPHABET.charAt(index);
                    ciphertext.append(encryptedChar);
                }
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    // Fungsi untuk mendekripsi teks
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (char c : ciphertext.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = CIPHER_ALPHABET.indexOf(c);
                if (index != -1) {
                    char decryptedChar = PLAIN_ALPHABET.charAt(index);
                    plaintext.append(decryptedChar);
                }
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Kami mahasiswa universitas sanata dharma";
        String encryptedText = encrypt(plaintext);
        String decryptedText = decrypt(encryptedText);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
