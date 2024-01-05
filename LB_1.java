Task1:
import java.util.*;

class Solution {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String operation = in.nextLine();
        int shift = in.nextInt();
        in.nextLine(); 

        String[] rotors = new String[3];
        for (int i = 0; i < 3; i++) {
            rotors[i] = in.nextLine();
        }

        String message = in.nextLine();

        String result = operation.equals("ENCODE") ? 
                        encode(message, shift, rotors) : 
                        decode(message, shift, rotors);

        System.out.println(result);
    }

    private static String encode(String message, int shift, String[] rotors) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            c = caesarShift(c, shift + i);
            for (String rotor : rotors) {
                c = rotor.charAt(ALPHABET.indexOf(c));
            }
            encoded.append(c);
        }
        return encoded.toString();
    }

    private static String decode(String message, int shift, String[] rotors) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            for (int j = rotors.length - 1; j >= 0; j--) {
                c = ALPHABET.charAt(rotors[j].indexOf(c));
            }
            c = caesarShift(c, -shift - i);
            decoded.append(c);
        }
        return decoded.toString();
    }

    private static char caesarShift(char c, int shift) {
        int shiftedIndex = (ALPHABET.indexOf(c) + shift) % ALPHABET.length();
        if (shiftedIndex < 0) {
            shiftedIndex += ALPHABET.length();
        }
        return ALPHABET.charAt(shiftedIndex);
    }
}
