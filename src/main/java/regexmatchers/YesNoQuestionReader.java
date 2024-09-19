package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YesNoQuestionReader implements UserAnswerReader<Boolean> {
    private static final String ANSWER_REGULAR_EXPRESSION = "Yes|No";
    private static final Pattern PATTERN =
            Pattern.compile(ANSWER_REGULAR_EXPRESSION, Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValidInput(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return matcher.matches();
    }

    @Override
    public Boolean parseInput(String input) {
        return input.equalsIgnoreCase("yes");
    }
    public static void main(String [] args) {
        YesNoQuestionReader yesNoQuestionReader = new YesNoQuestionReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (yesNoQuestionReader.isValidInput(input)) {
                System.out.println(String.format(
                        "The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        ANSWER_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + yesNoQuestionReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        ANSWER_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
