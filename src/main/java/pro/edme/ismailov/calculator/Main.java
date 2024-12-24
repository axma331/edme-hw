package pro.edme.ismailov.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static String calc(String input) throws Exception {
        int result, a, b;
        String trim = input.trim();

        Pattern pattern = Pattern.compile("^(\\d+)\\s*([+\\-*/])\\s*(\\d+)$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid input");

        a = Integer.parseInt(matcher.group(1));
        String operator = matcher.group(2);
        b = Integer.parseInt(matcher.group(3));

        if (a < 1 || a > 10 || b < 1 || b > 10)
            throw new IllegalArgumentException("Invalid input: Number should be between 1 and 10");

        result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0)
                    throw new IllegalArgumentException("Invalid input: Division by zero");
                yield a / b;
            }
            default -> throw new Exception("Unsupported operation");
        };
        return String.valueOf(result);
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            String input;

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                try {
                    System.out.println(calc(input));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
