package Enkripsi_Simetris;

public class CaesarCipher {

    public static String encrypt(String msg, int shiftKey) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char currentChar = msg.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                int encryptedChar = (currentChar - base + shiftKey) % 26 + base;

                encryptedMessage.append((char) encryptedChar);
            } else {
                encryptedMessage.append(currentChar);
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMsg, int shiftKey) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < encryptedMsg.length(); i++) {
            char currentChar = encryptedMsg.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                int decryptedChar = (currentChar - base - shiftKey + 26) % 26 + base;

                decryptedMessage.append((char) decryptedChar);
            } else {
                decryptedMessage.append(currentChar);
            }
        }
        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        String message = "Kami mahasiswa universitas sanata dharma";
        int shiftKey = 3;

        String encryptedMessage = encrypt(message, shiftKey);
        String decryptedMessage = decrypt(encryptedMessage, shiftKey);

        System.out.println("Kata Asli : " + message);
        System.out.println("Encrypted Message (Metode Caesar Cipher) : " + encryptedMessage);
        System.out.println("Decrypted Message : " + decryptedMessage);
    }
}
