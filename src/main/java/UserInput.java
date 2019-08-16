import java.util.ArrayList;

public class UserInput {
    public static ArrayList<Die> processRollsInput(String diceInputString) {
        final int NUMBER_OF_DICE_REQUIRED = 5;
        String[] rollsAsStrings = diceInputString.split(",");

        if (rollsAsStrings.length != NUMBER_OF_DICE_REQUIRED) {
            throw new InvalidInputException("Error: 5 dice must be used");
        }

        ArrayList<Die> dice = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DICE_REQUIRED; ++i) {
            int roll = Integer.parseInt(rollsAsStrings[i]);
            if (Die.MIN_VALUE <= roll && roll <= Die.MAX_VALUE) {
                dice.add(new Die(roll));
            }
            else {
                throw new InvalidInputException("Error: Rolls must be between " + Die.MIN_VALUE + " and " + Die.MAX_VALUE);
            }

        }
        return dice;
    }

    public static Category processCategoryInput(String categoryInputString) {
        switch (categoryInputString) {
            case "chance":
                return Category.CHANCE;
            case "yatzy":
                return Category.YATZY;
            case "ones":
                return Category.ONES;
            case "twos":
                return Category.TWOS;
            case "threes":
                return Category.THREES;
            case "fours":
                return Category.FOURS;
            case "fives":
                return Category.FIVES;
            case "sixes":
                return Category.SIXES;
            case "pair":
                return Category.PAIR;
            case "two pair":
                return Category.TWO_PAIR;
            case "three of a kind":
                return Category.THREE_OF_A_KIND;
            case "four of a kind":
                return Category.FOUR_OF_A_KIND;
            case "small straight":
                return Category.SMALL_STRAIGHT;
            case "large straight":
                return Category.LARGE_STRAIGHT;
            case "full house":
                return Category.FULL_HOUSE;
            default:
                throw new InvalidCategoryException("Error: Category not recognised");
        }
    }
}
