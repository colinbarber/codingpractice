package Challenges;
/*  foo.bar level 1 - Braille Translation */

public class ConvertBraille {
    public static String main(String plaintext) {

        StringBuilder ans = new StringBuilder();

        // loop through input string
        for (int i = 0; i < plaintext.length(); i++) {

            ans.append(convertBraille(plaintext.charAt(i)));
        }

        return String.valueOf(ans);
    }

    private static String convertBraille(Character ch) {

        StringBuilder convert = new StringBuilder();

        if (Character.isUpperCase(ch)) {
            convert.append("000001");
        }

        switch (Character.toLowerCase(ch)) {
            case ' ': convert.append("000000"); break;
            case 'a': convert.append("100000"); break;
            case 'b': convert.append("110000"); break;
            case 'c': convert.append("100100"); break;
            case 'd': convert.append("100110"); break;
            case 'e': convert.append("100010"); break;
            case 'f': convert.append("110100"); break;
            case 'g': convert.append("110110"); break;
            case 'h': convert.append("110010"); break;
            case 'i': convert.append("010100"); break;
            case 'j': convert.append("010110"); break;
            case 'k': convert.append("101000"); break;
            case 'l': convert.append("111000"); break;
            case 'm': convert.append("101100"); break;
            case 'n': convert.append("101110"); break;
            case 'o': convert.append("101010"); break;
            case 'p': convert.append("111100"); break;
            case 'q': convert.append("111110"); break;
            case 'r': convert.append("111010"); break;
            case 's': convert.append("011100"); break;
            case 't': convert.append("011110"); break;
            case 'u': convert.append("101001"); break;
            case 'v': convert.append("111001"); break;
            case 'w': convert.append("010111"); break;
            case 'x': convert.append("101101"); break;
            case 'y': convert.append("101111"); break;
            case 'z': convert.append("101011"); break;
        }

        return String.valueOf(convert);
    }
}

