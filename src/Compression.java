public class Compression {

    public static String compress(String input) {
        if(input == null) return null;
        StringBuilder result = new StringBuilder();

        int count = 0;
        for(int i = 0; i < input.length(); i++) {
            if(i == 0) {
                count++;
            } else {
                if(input.charAt(i) == input.charAt(i-1)) {
                    count++;
                } else {
                    result.append(input.charAt(i-1)).append("±").append(count);
                    count = 1;
                }
                if(i == input.length() - 1) {
                    result.append(input.charAt(i)).append("±").append(count);
                }
            }
        }

        return result.toString();
    }

    public static String decompress(String input) {
        if(input == null) return null;
        StringBuilder result = new StringBuilder();

        char character;
        StringBuilder digitString;
        for(int i = 0; i < input.length(); i++) {
            character = input.charAt(i);
            if(input.charAt(i+1) != 177) throw new IllegalArgumentException("Input isn't compressed correctly.");

            digitString = new StringBuilder();

            for(int j = i + 2; j < input.length(); j++) {
                if(Character.isDigit(input.charAt(j))) {
                    if(j + 1 >= input.length()) {
                        digitString.append(input.charAt(j));
                        i = j + 1;
                        break;
                    } else if(input.charAt(j+1) != 177 || input.charAt(j+2) == 177) {
                        digitString.append(input.charAt(j));
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
                result.append(String.valueOf(character).repeat(Math.max(0, Integer.parseInt(digitString.toString()))));
            }

        }
        return result.toString();
    }
}
