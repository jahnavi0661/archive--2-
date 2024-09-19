package chatbots.restaurant;

import chatbots.MealTime;
import regexmatchers.*;

import java.time.LocalDate;
import java.util.Scanner;

public class ChatBotApplication {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        RestaurantReservationChatBot chatBot = new RestaurantReservationChatBot(scanner);

        MealTime mealTime = chatBot.readMealTime(new YesNoQuestionReader());
        FullNameReader.FullName name = chatBot.readFullName(new FullNameReader());
        String phoneNumber = chatBot.readPhoneNumber(new PhoneNumberReader());
        LocalDate date = chatBot.readDate(new DateReader());
        int numberOfVisitors = chatBot.getNumberOfPeople(new NumberChoiceReader());

        chatBot.makeReservation(name, phoneNumber, numberOfVisitors, date, mealTime);
        chatBot.registerForPromotions(new YesNoQuestionReader(), new EmailReader());
    }
}
