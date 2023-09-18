package Enkripsi_Simetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CipherHomophonicStatic {
     private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";

    private static Map<Character, String> substitutionMap;

    public static void main(String[] args) {
        initializeSubstitutionMap();
        String plaintext = "kami mahasiswa universitas sanata dharma";
        String ciphertext = encrypt(plaintext);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static void initializeSubstitutionMap() {
        substitutionMap = new HashMap<>();
        String homophonicAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // You can customize this
        Random random = new Random();

        for (char c : ALPHABET.toCharArray()) {
            StringBuilder homophonicChars = new StringBuilder();
            int numHomophonicChars = random.nextInt(3) + 1; // Random number of homophonic characters (1 to 3)
            for (int i = 0; i < numHomophonicChars; i++) {
                char homophonicChar = homophonicAlphabet.charAt(random.nextInt(homophonicAlphabet.length()));
                homophonicChars.append(homophonicChar);
            }
            substitutionMap.put(c, homophonicChars.toString());
        }
    }

    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                String homophonicChars = substitutionMap.get(c);
                ciphertext.append(homophonicChars);
            } else {
                // Non-alphanumeric characters remain unchanged
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

public static String decrypt(String ciphertext) {
    StringBuilder decryptedText = new StringBuilder();
    int i = 0;
    while (i < ciphertext.length()) {
        char c = ciphertext.charAt(i);
        if (Character.isLetter(c) || Character.isDigit(c)) {
            StringBuilder homophonicChars = new StringBuilder();
            while (i < ciphertext.length() && (Character.isLetter(c) || Character.isDigit(c))) {
                homophonicChars.append(c);
                i++;
            }
            // Now we need to split homophonicChars into individual homophonic characters
            String[] homophonicArray = homophonicChars.toString().split(" ");
            for (String homophonic : homophonicArray) {
                char originalChar = findKeyByValue(homophonic);
                decryptedText.append(originalChar);
            }
        } else {
            // Non-alphanumeric characters remain unchanged
            decryptedText.append(c);
            i++;
        }
    }
    return decryptedText.toString();
}


    private static char findKeyByValue(String value) {
        for (Map.Entry<Character, String> entry : substitutionMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 'e'; // Return '?' if no matching key is found
    }
}