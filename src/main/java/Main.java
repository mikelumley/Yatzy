import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("Welcome to Yazty");

        System.out.println("Enter 5 dice rolls:");
        String diceRolls = keyboardInput.nextLine();
        ArrayList<Die> dice = UserInput.processRollsInput(diceRolls);

        System.out.println("Enter a category:");
        String category = keyboardInput.nextLine();

        int score = Yatzy.calcScore(dice, category);
        System.out.println("Your score is:");
        System.out.println(score);
    }
}
