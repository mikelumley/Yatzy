import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("Welcome to Yazty");
        System.out.println("You rolled:");
        ArrayList<Die> dice = Main.setupDice(2,3,4,2,5);

        String diceAsString = Main.printDice(dice);
        System.out.println("(" + diceAsString + ")");

        System.out.println("Select a category:");
        String category = keyboardInput.nextLine();

        try {
            int score = Yatzy.calcScore(dice, category);
            System.out.println("Your score is:");
            System.out.println(score);
        } catch(InvalidCategoryException e) {
            System.out.println(e.toString());
        }
    }

    private static ArrayList<Die> setupDice(int d1, int d2, int d3, int d4, int d5) {
        ArrayList<Die> dice = new ArrayList<>();
        dice.add(new Die(d1));
        dice.add(new Die(d2));
        dice.add(new Die(d3));
        dice.add(new Die(d4));
        dice.add(new Die(d5));
        return dice;
    }

    private static String printDice(ArrayList<Die> dice) {
        StringJoiner diceAsString = new StringJoiner(", ");
        for(Die die : dice) {
            String currentRollAsString = String.valueOf(die.getValue());
            diceAsString.add(currentRollAsString);
        }
        return diceAsString.toString();
    }
}
