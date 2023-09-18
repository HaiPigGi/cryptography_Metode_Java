package Enkripsi_Simetris;

public class test {
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        // Menghapus spasi dari pesan
        plaintext = plaintext.replaceAll(" ", "");

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            char keyChar = key.charAt(j % key.length());

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                int encryptedChar = (currentChar - base + keyChar - 'A') % 26 + base;

                ciphertext.append((char) encryptedChar);
                j++;
            } else {
                // Jika karakter bukan huruf, biarkan seperti itu tanpa perubahan
                ciphertext.append(currentChar);
            }
        }
        return ciphertext.toString();
    }

    public static void main(String[] args) {
        String plaintext1 = "musik";
        String key1 = "LAMPION";

        String ciphertext1 = encrypt(plaintext1, key1);
        System.out.println("Contoh 1 - Ciphertext: " + ciphertext1);

        String plaintext2 = "SHE SELLS SEA SHELLS BY THE SEASHORE";
        String key2 = "KEY";

        String ciphertext2 = encrypt(plaintext2, key2);
        System.out.println("Contoh 2 - Ciphertext: " + ciphertext2);
    }
}
