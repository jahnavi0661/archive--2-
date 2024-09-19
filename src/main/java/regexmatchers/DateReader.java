package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateReader implements UserAnswerReader<LocalDate> {
    private static final String DATE_REGULAR_EXPRESSION =
            ".*(?<month>\\d{1,2})(?<dateDelimiter>[./])" +
                    "(?<day>\\d{1,2})\\k<dateDelimiter>(?<year>\\d{4})";

    @Override
    public boolean isValidInput(String input) {
        return Pattern.matches(DATE_REGULAR_EXPRESSION, input);
    }

    @Override
    public LocalDate parseInput(String input) {
        Pattern pattern = Pattern.compile(DATE_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();

        int month = Integer.parseInt(matcher.group("month"));
        int day = Integer.parseInt(matcher.group("day"));
        int year = Integer.parseInt(matcher.group("year"));
        return LocalDate.of(year, month, day);
    }

   
    public static void main(String [] args) {
        DateReader dateReader = new DateReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (dateReader.isValidInput(input)) {
                System.out.println(String.format(
                        "The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        DATE_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + dateReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        DATE_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
