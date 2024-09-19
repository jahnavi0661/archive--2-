package chatbots.restaurant;

import chatbots.MealTime;
import regexmatchers.FullNameReader;
import regexmatchers.interfaces.UserAnswerReader;

import java.time.LocalDate;
import java.util.Scanner;


public class RestaurantReservationChatBot {

    private final Scanner scanner;

    public RestaurantReservationChatBot(Scanner scanner) {
        this.scanner = scanner;
    }

    public MealTime readMealTime(UserAnswerReader<Boolean> userAnswerReader) {
        String input;
        for (MealTime mealTime : MealTime.values()) {
            do {
                System.out.println(String.format("Would you like to make a reservation for %s?", mealTime.getMeanTimeValue()));
                input = scanner.nextLine();
            } while (!userAnswerReader.isValidInput(input));
            if (userAnswerReader.parseInput(input)) {
                System.out.println(String.format("Great, thank you for choosing to eat %s at our restaurant!", mealTime.getMeanTimeValue()));
                return mealTime;
            }
        }
        System.out.println(String.format("Unfortunately we only have %s options, please come back later.",
                MealTime.getAllStringValues()));
        System.exit(0);
        return null;
    }

    public FullNameReader.FullName readFullName(UserAnswerReader<FullNameReader.FullName> userAnswerReader) {
        String input;
        do {
            System.out.println("What is your full name");
            input = scanner.nextLine();
        } while (!userAnswerReader.isValidInput(input));

        FullNameReader.FullName name = userAnswerReader.parseInput(input);

        System.out.println("Welcome " + name.getFirstName() + " to our reservation system:)");
        return name;
    }

    public String readPhoneNumber(UserAnswerReader<String> userAnswerReader) {
        String input;
        do {
            System.out.println("What is the best phone number to reach you for reservation reminders?");
            input = scanner.nextLine();
        } while (!userAnswerReader.isValidInput(input));

        String canonicalPhoneNumber =  userAnswerReader.parseInput(input);

        System.out.println("Great, you will get a text message to " + canonicalPhoneNumber + ", 24 before your reservation.");
        return canonicalPhoneNumber;
    }

    public LocalDate readDate(UserAnswerReader<LocalDate> userAnswerReader) {
        System.out.println("Alright! Let's make that reservation.");
        String input;
        do {
            System.out.println("What date would you like to come in?");
            input = scanner.nextLine();
        } while (!userAnswerReader.isValidInput(input));

        LocalDate date = userAnswerReader.parseInput(input);

        System.out.println("Great news, we have plenty of tables available on " + date + "!");

        return date;
    }

    public int getNumberOfPeople(UserAnswerReader<Integer> userAnswerReader) {
        String input;
        do {
            System.out.println("How many people will be dinning with us?");
            input = this.scanner.nextLine();
        } while (!userAnswerReader.isValidInput(input));

        int numberOfVisitors = userAnswerReader.parseInput(input);

        System.out.println(String.format("Great! %d is a perfect number for a dinning party at our restaurant.", numberOfVisitors));

        return numberOfVisitors;
    }

    public void registerForPromotions(UserAnswerReader<Boolean> yesNoQuestionReader,
                                      UserAnswerReader<String> emailReader) {
        String input;
        do {
            System.out.println("Would you like to sign up for our promotions and deals?");
            input = scanner.nextLine();
        } while (!yesNoQuestionReader.isValidInput(input));

        if (!yesNoQuestionReader.parseInput(input)) {
            System.out.println("No worries!");
            System.out.println("See you soon!");
            return;
        }

        System.out.println("Awesome!");
        do {
            System.out.println("What is your email address?");
            input = scanner.nextLine();
        } while (!emailReader.isValidInput(input));

        String email = emailReader.parseInput(input);

        System.out.println(String.format("Thank you for providing your email %s.", email));
        System.out.println("You will now receive our promotions and deals every week.");
        System.out.println("See you soon!");
    }

    public void makeReservation(FullNameReader.FullName fullName,
                                String phoneNumber,
                                int numberOfVisitors,
                                LocalDate date,
                                MealTime mealTime) {
        System.out.println("A reservation has been made on the name of:");
        System.out.println(String.format("%s %s, phone number: %s",
                fullName.getFirstName(),
                fullName.getLastName(),
                phoneNumber));
        System.out.println(String.format("We look forward to your party of %d, coming for %s, on %s",
                numberOfVisitors,
                mealTime.getMeanTimeValue(),
                date));
    }
}
