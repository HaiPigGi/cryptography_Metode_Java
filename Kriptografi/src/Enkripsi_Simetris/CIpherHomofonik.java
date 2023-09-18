package Enkripsi_Simetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CIpherHomofonik {
    // Fungsi untuk membuat kunci acak
    public static Map<Character, String[]> generateHomophonicKey() {
        Map<Character, String[]> key = new HashMap<>();
        Random random = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (char letter : alphabet.toCharArray()) {
            int numSymbols = random.nextInt(4) + 2; // Pilih antara 2 hingga 5 simbol acak untuk setiap huruf
            String[] symbols = new String[numSymbols];

            for (int i = 0; i < numSymbols; i++) {
                char symbol = (char) (random.nextInt(26) + 'a'); // Pilih simbol acak dari alfabet kecil
                symbols[i] = String.valueOf(symbol);
            }

            key.put(letter, symbols);
        }

        return key;
    }
    
    // Fungsi untuk mengenkripsi teks
    public static String encryptHomophonic(String text, Map<Character, String[]> key) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                String[] symbols = key.get(c);
                String symbol = symbols[new Random().nextInt(symbols.length)];

                encryptedText.append(symbol);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }



    public static void main(String[] args) {
        Map<Character, String[]> key = generateHomophonicKey();
        String plaintext = "Kami mahasiswa universitas sanata dharma".toLowerCase(); // Mengubah teks asli menjadi huruf kecil
        String encryptedText = encryptHomophonic(plaintext, key);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("");

        // Menampilkan kunci homofonik
        for (Map.Entry<Character, String[]> entry : key.entrySet()) {
            char letter = entry.getKey();
            String[] symbols = entry.getValue();
            System.out.print("Letter: " + letter + " Symbols: ");
            for (String symbol : symbols) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
}
