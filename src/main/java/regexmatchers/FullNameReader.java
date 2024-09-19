package regexmatchers;

import regexmatchers.interfaces.UserAnswerReader;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameReader implements UserAnswerReader<FullNameReader.FullName> {
    private static final String FULL_NAME_REGULAR_EXPRESSION =
            "([A-Z][a-z]+)\\s([A-Z][a-z]+)";

    @Override
    public boolean isValidInput(String input) {
        return Pattern.matches(FULL_NAME_REGULAR_EXPRESSION, input);
    }

    @Override
    public FullName parseInput(String input) {
        Pattern pattern = Pattern.compile(FULL_NAME_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();

        String firstName = matcher.group(1);
        String lastName = matcher.group(2);
        return new FullName(firstName, lastName);
    }

    public static final class FullName {
        private final String firstName;
        private final String lastName;

        public FullName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            return "FullName{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        FullNameReader fullNameReader = new FullNameReader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type in your input:");
            String input = scanner.nextLine();
            if (fullNameReader.isValidInput(input)) {
                System.out.println(String.format(
                        "The input: \"%s\" matches the regular expression \"%s\"",
                        input,
                        FULL_NAME_REGULAR_EXPRESSION));
                System.out.println("The parsed value is " + fullNameReader.parseInput(input));
            } else {
                System.out.println(String.format(
                        "The input: \"%s\" does NOT match the regular expression \"%s\"",
                        input,
                        FULL_NAME_REGULAR_EXPRESSION));
            }
            System.out.println();
        }
    }
}
