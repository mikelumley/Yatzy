import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("Welcome to Yazty");

        ArrayList<Die> dice = Main.askForDiceRolls(keyboardInput);
        Category category = Main.askForCategory(keyboardInput);
        int score = Yatzy.calcScore(dice, category);

        System.out.println("Your score is:");
        System.out.println(score);
    }

    private static ArrayList<Die> askForDiceRolls(Scanner keyboardInput) {
        try {
            System.out.println("Enter 5 dice rolls:");
            String diceRolls = keyboardInput.nextLine();
            return UserInput.processRollsInput(diceRolls);
        } catch (InvalidInputException error) {
            System.out.println(error.toString());
            return Main.askForDiceRolls(keyboardInput);
        }
    }

    private static Category askForCategory(Scanner keyboardInput) {
        try {
            System.out.println("Enter a category:");
            String category = keyboardInput.nextLine();
            return UserInput.processCategoryInput(category);
        } catch (InvalidCategoryException error) {
            System.out.println(error.toString());
            return Main.askForCategory(keyboardInput);
        }
    }
}
