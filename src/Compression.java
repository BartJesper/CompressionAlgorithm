public class Compression {

    public static String compress(String input) {
        char[] charArray = input.toCharArray();
        StringBuilder result = new StringBuilder();

        int count = 0;
        for(int i = 0; i < charArray.length; i++) {
            if(i == 0) {
                count++;
            } else {
                if(charArray[i] == charArray[i-1]) {
                    count++;
                } else {
                    result.append(charArray[i - 1]).append("±").append(count);
                    count = 1;
                }
                if(i == charArray.length - 1) {
                    result.append(charArray[i]).append("±").append(count);
                }
            }
        }

        return result.toString();
    }

    public static String decompress(String input) {
        char[] charArray = input.toCharArray();
        StringBuilder result = new StringBuilder();

        char character;
        for(int i = 0; i < charArray.length; i++) {
            character = charArray[i];
            if(charArray[i+1] != 177) throw new IllegalArgumentException("Input isn't compressed correctly.");

            StringBuilder digitString = new StringBuilder();

            for(int j = i + 2; j < charArray.length; j++) {
                if(Character.isDigit(charArray[j])) {
                    if(j + 1 >= charArray.length) {
                        digitString.append(charArray[j]);
                        i = j + 1;
                        break;
                    } else if(charArray[j+1] != 177 || charArray[j+2] == 177) {
                        digitString.append(charArray[j]);
                    } else {
                        i = j - 1;
                        break;
                    }
                } else {
                    i = j - 1;
                    break;
                }
            }

            if(digitString.toString().isEmpty()) {
                throw new IllegalArgumentException("Input isn't compressed correctly.");
            } else {
                for(int j = 0; j < Integer.parseInt(digitString.toString()); j++) {
                    result.append(character);
                }
            }

        }
        return result.toString();
    }
}
