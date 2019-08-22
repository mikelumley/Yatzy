import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IKeyboardInput keyboardInput = new KeyboardInput();

        System.out.println("Welcome to Yazty");

        ArrayList<Die> dice = UserInput.askForDiceRolls(keyboardInput);
        Category category = UserInput.askForCategory(keyboardInput);
        int score = Yatzy.calcScore(dice, category);

        System.out.println("Your score is:");
        System.out.println(score);
    }
}
