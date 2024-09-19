package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneNumberReader implements UserAnswerReader<String> {
    private static final String PHONE_NUMBER_REGULAR_EXPRESSION =
            "(?<areaCode>\\d{3})-?(?<exchangeCode>\\d{3})-?(?<lineNumber>\\d{4})";

    @Override
    public boolean isValidInput(String input) {
        return Pattern.matches(PHONE_NUMBER_REGULAR_EXPRESSION, input);
    }

    @Override
    public String parseInput(String input) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();

        String areaCode = matcher.group("areaCode");
        String exchangeCode = matcher.group("exchangeCode");
        String lineNumber = matcher.group("lineNumber");

        return String.format("(%s)-%s-%s", areaCode, exchangeCode, lineNumber);
    }

    
    public static void main(String [] args) {
        PhoneNumberReader phoneNumberReader  = new PhoneNumberReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (phoneNumberReader.isValidInput(input)) {
                System.out.println(String.format("The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        PHONE_NUMBER_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + phoneNumberReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        PHONE_NUMBER_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
