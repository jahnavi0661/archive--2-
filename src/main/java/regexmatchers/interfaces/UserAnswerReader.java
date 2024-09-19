package regexmatchers.interfaces;


public interface UserAnswerReader<T> {
    boolean isValidInput(String input);

    T parseInput(String input);
}
