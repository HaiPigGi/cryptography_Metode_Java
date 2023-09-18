package Enkripsi_Simetris;

public class PlayfairCipher {
    private char[][] keyMatrix;

    public PlayfairCipher(String key) {
        initializeKeyMatrix(key);
    }

    private void initializeKeyMatrix(String key) {
        // Inisialisasi matriks kunci dengan huruf-huruf dari kunci
        keyMatrix = new char[5][5];
        String keyWithoutDuplicates = removeDuplicateCharacters(key + "ABCDEFGHIKLMNOPQRSTUVWXYZ");
        int row = 0, col = 0;

        for (char c : keyWithoutDuplicates.toCharArray()) {
            keyMatrix[row][col] = c;
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    private String removeDuplicateCharacters(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (result.indexOf(String.valueOf(c)) == -1) {
                result.append(c);
            }
        }
        return result.toString();
    }

     private int[] findCharInKeyMatrix(char target) {
        int[] position = new int[2];

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (keyMatrix[row][col] == target) {
                    position[0] = row;
                    position[1] = col;
                    return position;
                }
            }
        }

        return position;
    }
    

    public String encrypt(String plaintext) {
    StringBuilder ciphertext = new StringBuilder();
    plaintext = plaintext.replaceAll("J", "I"); // Replace J with I in the plaintext
    plaintext = plaintext.replaceAll("\\s+", ""); // Remove spaces from the plaintext

    for (int i = 0; i < plaintext.length(); i += 2) {
        char firstChar = plaintext.charAt(i);
        char secondChar = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

        int[] firstCharPos = findCharInKeyMatrix(firstChar);
        int[] secondCharPos = findCharInKeyMatrix(secondChar);

        if (firstCharPos[0] == secondCharPos[0]) {
            // If both characters are in the same row, replace with the characters to their right
            ciphertext.append(keyMatrix[firstCharPos[0]][(firstCharPos[1] + 1) % 5]);
            ciphertext.append(keyMatrix[secondCharPos[0]][(secondCharPos[1] + 1) % 5]);
        } else if (firstCharPos[1] == secondCharPos[1]) {
            // If both characters are in the same column, replace with the characters below them
            ciphertext.append(keyMatrix[(firstCharPos[0] + 1) % 5][firstCharPos[1]]);
            ciphertext.append(keyMatrix[(secondCharPos[0] + 1) % 5][secondCharPos[1]]);
        } else {
            // If the characters are in different rows and columns, form a rectangle and replace
            ciphertext.append(keyMatrix[firstCharPos[0]][secondCharPos[1]]);
            ciphertext.append(keyMatrix[secondCharPos[0]][firstCharPos[1]]);
        }
    }

    return ciphertext.toString();
}


    public String decrypt(String ciphertext) {
    StringBuilder plaintext = new StringBuilder();

    for (int i = 0; i < ciphertext.length(); i += 2) {
        char firstChar = ciphertext.charAt(i);
        char secondChar = ciphertext.charAt(i + 1);

        int[] firstCharPos = findCharInKeyMatrix(firstChar);
        int[] secondCharPos = findCharInKeyMatrix(secondChar);

        if (firstCharPos[0] == secondCharPos[0]) {
            // If both characters are in the same row, replace with the characters to their left
            plaintext.append(keyMatrix[firstCharPos[0]][(firstCharPos[1] + 4) % 5]);
            plaintext.append(keyMatrix[secondCharPos[0]][(secondCharPos[1] + 4) % 5]);
        } else if (firstCharPos[1] == secondCharPos[1]) {
            // If both characters are in the same column, replace with the characters above them
            plaintext.append(keyMatrix[(firstCharPos[0] + 4) % 5][firstCharPos[1]]);
            plaintext.append(keyMatrix[(secondCharPos[0] + 4) % 5][secondCharPos[1]]);
        } else {
            // If the characters are in different rows and columns, form a rectangle and replace
            plaintext.append(keyMatrix[firstCharPos[0]][secondCharPos[1]]);
            plaintext.append(keyMatrix[secondCharPos[0]][firstCharPos[1]]);
        }
    }

    return plaintext.toString();
}


    public static void main(String[] args) {
        String key = "KEY";
        String plaintext = "HELLO WORLD";

        PlayfairCipher playfair = new PlayfairCipher(key);
        String ciphertext = playfair.encrypt(plaintext);
        System.out.println("Encrypted: " + ciphertext);

        String decryptedText = playfair.decrypt(ciphertext);
        System.out.println("Decrypted: " + decryptedText);
    }
}
