import java.util.ArrayList;

public class UserInput {
    public static ArrayList<Die> processRollsInput(String diceInputString) {
        final int NUMBER_OF_DICE_REQUIRED = 5;
        String[] rollsAsStrings = diceInputString.split(",");

        if(rollsAsStrings.length != NUMBER_OF_DICE_REQUIRED) {
            throw new InvalidInputException("Error: 5 dice must be used");
        }

        ArrayList<Die> dice = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_DICE_REQUIRED; ++i) {
            int roll = Integer.parseInt(rollsAsStrings[i]);
            if(Die.MIN_VALUE <= roll && roll <= Die.MAX_VALUE) {
                dice.add(new Die(roll));
            }
            else {
                throw new InvalidInputException("Error: Rolls must be between " + Die.MIN_VALUE + " and " + Die.MAX_VALUE);
            }

        }
        return dice;
    }
}
