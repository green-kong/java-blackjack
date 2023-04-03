package utils;

public class Converter {
    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자만 입력가능합니다.");
        }
    }
}
