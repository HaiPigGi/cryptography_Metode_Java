import java.awt.Point;
import java.util.Scanner;

public class PlayFairCipher {

    private int length = 0;
    private String[][] table;

    public PlayFairCipher(String key) {
        table = cipherTable(key);
    }

    public static void main(String[] args) {
        System.out.println("Playfair Cipher");
        System.out.println("===============================");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key for the Playfair Cipher: ");
        String key = parseString(scanner);
        while (key.equals("")) {
            key = parseString(scanner);
        }

        PlayFairCipher playFairCipher = new PlayFairCipher(key);

        System.out.print("Enter the plain text: ");
        String input = parseString(scanner);
        while (input.equals("")) {
            input = parseString(scanner);
        }

        String encryptedText = playFairCipher.cipher(input);
        String decryptedText = playFairCipher.decode(encryptedText);

        System.out.println("Playfair Cipher Key Matrix:");
        playFairCipher.keyTable(playFairCipher.getTable());

        System.out.println("Encrypted Message: " + encryptedText);
        System.out.println("Decrypted Message: " + decryptedText);
    }

    private static String parseString(Scanner scan) {
        String parse = scan.nextLine();
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    private String[][] cipherTable(String key) {
        String[][] playFairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        boolean[] usedLetters = new boolean[26]; // To keep track of used letters

        int k = 0; // Index for the keyString

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char currentChar;

                // Find the next unused character in the keyString
                while (usedLetters[keyString.charAt(k) - 'A']) {
                    k++;
                }

                currentChar = keyString.charAt(k);
                playFairTable[i][j] = String.valueOf(currentChar);
                usedLetters[currentChar - 'A'] = true;
                k++;
            }
        }

        return playFairTable;
    }

    private String cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;

        for (int i = 0; i < (length - 1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }
        String[] diagraph = new String[length];

        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && in.length() / 2 == (length - 1)) {
                in = in + 'X';
            }
            diagraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }

        String[] encDiagraphs = encodeDigraph(diagraph);

        String out = "";
        for (int k = 0; k < length; k++) {
            out = out + encDiagraphs[k];
        }
        return out;
    }

    private String[] encodeDigraph(String di[]) {
        String[] encipher = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            encipher[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return encipher;
    }

    private String decode(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        return decoded;
    }

    private Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) {
                    pt = new Point(i, j);
                }
            }
        }
        return pt;
    }

    private void keyTable(String[][] printTable) {
        System.out.println("Playfair Cipher Key Matrix:");
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(printTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private String[][] getTable() {
        return table;
    }

    private void printResults(String encipher, String dec) {
        System.out.print("Encrypted Message: ");
        System.out.println(encipher);
        System.out.println();
        System.out.print("Decrypted Message: ");
        System.out.println(dec);
    }
}
