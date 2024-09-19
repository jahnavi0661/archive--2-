package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberChoiceReader implements UserAnswerReader<Integer> {
    private static final String NUMBER_REGULAR_EXPRESSION = ".*\\b(\\d{1,3})\\b.*";

    @Override
    public boolean isValidInput(String input) {
        return Pattern.matches(NUMBER_REGULAR_EXPRESSION, input);
    }

    @Override
    public Integer parseInput(String input) {
        Pattern pattern = Pattern.compile(NUMBER_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();

        String number = matcher.group(1);
        return Integer.parseInt(number);
    }

    public static void main(String [] args) {
        NumberChoiceReader numberChoiceReader = new NumberChoiceReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (numberChoiceReader.isValidInput(input)) {
                System.out.println(String.format(
                        "The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        NUMBER_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + numberChoiceReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        NUMBER_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
