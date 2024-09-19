package chatbots;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MealTime {
    BREAKFAST("breakfast"),
    LUNCH("lunch"),
    DINNER("dinner");

    private final String meanTimeValue;

    MealTime(String meanTimeValue) {
        this.meanTimeValue = meanTimeValue;
    }

    public static List<String> getAllStringValues() {
        return Stream.of(MealTime.values())
                .map(value -> value.toString()
                        .toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    public String getMeanTimeValue() {
        return meanTimeValue;
    }
}
