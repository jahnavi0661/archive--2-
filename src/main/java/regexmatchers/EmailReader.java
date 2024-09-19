package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailReader implements UserAnswerReader<String> {
    private static final String EMAIL_REGULAR_EXPRESSION =
            "\\b[.\\w]{1,25}@(gmail|yahoo|bing|chitkara.edu.in)\\.com\\b";

    @Override
    public boolean isValidInput(String input) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher matcher = emailPattern.matcher(input);

        int numberOfMatches = 0;

        while (matcher.find()) {
            numberOfMatches++;
        }
        return numberOfMatches == 1;
    }

    @Override
    public String parseInput(String input) {
        Pattern pattern = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        return matcher.group(0);
    }

    public static void main(String [] args) {
        EmailReader emailReader = new EmailReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (emailReader.isValidInput(input)) {
                System.out.println(String.format(
                        "The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        EMAIL_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + emailReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        EMAIL_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
