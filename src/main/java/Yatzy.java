import java.util.*;

public class Yatzy {

    private static final int YATZY_SCORE = 50;

    public static void main(String[] args) {


        System.out.println("Welcome to Yazty");
        System.out.println("You rolled:");
        ArrayList<Die> dice = Yatzy.setupDice(2,3,4,2,5);

        String diceAsString = Yatzy.printDice(dice);
        System.out.println("(" + diceAsString + ")");

        System.out.println("Select a category:");
        String category = Yatzy.getCategoryFromUser();

        System.out.println("Your score is:");
        try {
            System.out.println(Yatzy.calcScore(dice, category));
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

    private static String getCategoryFromUser() {
        Scanner keyboardInput = new Scanner(System.in);
        String category = keyboardInput.nextLine();
        return category;
    }

    public static int calcScore(ArrayList<Die> dice, String category) throws InvalidCategoryException {
        String categoryAsLowerCase = category.toLowerCase();
        switch (categoryAsLowerCase) {
            case "chance":
                return Yatzy.calcChanceScore(dice);
            case "yatzy":
                return Yatzy.calcYatzyScore(dice);
            case "ones":
                return Yatzy.calcSingleNumberScore(1, dice);
            case "twos":
                return Yatzy.calcSingleNumberScore(2, dice);
            case "threes":
                return Yatzy.calcSingleNumberScore(3, dice);
            case "fours":
                return Yatzy.calcSingleNumberScore(4, dice);
            case "fives":
                return Yatzy.calcSingleNumberScore(5, dice);
            case "sixes":
                return Yatzy.calcSingleNumberScore(6, dice);
            case "pair":
                return Yatzy.calcPairScore(dice);
            case "2 pair":
                return Yatzy.calcTwoPairScore(dice);
            case "3 of a kind":
                return Yatzy.calcThreeOfAKindScore(dice);
            case "4 of a kind":
                return Yatzy.calcFourOfAKindScore(dice);
            case "small straight":
                return Yatzy.calcSmallStraightScore(dice);
            case "large straight":
                return Yatzy.calcLargeStraightScore(dice);
            case "full house":
                return Yatzy.calcFullHouseScore(dice);
            default:
                throw new InvalidCategoryException("Error: Category not recognized");
        }
    }

    public static int calcChanceScore(ArrayList<Die> dice) {
        int score = 0;
        for(Die die : dice) {
            int currentRoll = die.getValue();
            score += currentRoll;
        }
        return score;
    }

    public static int calcYatzyScore(ArrayList<Die> dice) {
        int firstDieRoll = dice.get(0).getValue();
        for(Die die : dice) {
            int currentRoll = die.getValue();
            if(currentRoll != firstDieRoll) {
                return 0;
            }
        }
        return YATZY_SCORE;
    }

    public static int calcSingleNumberScore(int number, ArrayList<Die> dice) {
        int score = 0;
        for(Die die : dice) {
            int currentRoll = die.getValue();
            if(number == currentRoll) {
                score += number;
            }
        }
        return score;
    }

    public static int calcPairScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = Yatzy.getRollCounts(dice);
        int pairRoll = findRollThatOccurredNTimes(rollCounts, 2);
        return pairRoll * 2;
    }

    public static int calcTwoPairScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = Yatzy.getRollCounts(dice);
        int numberOfPairsSeen = 0;
        int score = 0;

        for(int rollValue : rollCounts.keySet()) {
            if(rollCounts.get(rollValue) >= 2) {
                numberOfPairsSeen++;
                score += rollValue * 2;
            }
        }
        return numberOfPairsSeen == 2 ? score : 0;
    }

    public static int calcThreeOfAKindScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = Yatzy.getRollCounts(dice);
        int threeOfAKindRoll = findRollThatOccurredNTimes(rollCounts, 3);
        return threeOfAKindRoll * 3;
    }

    public static int calcFourOfAKindScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = Yatzy.getRollCounts(dice);
        int fourOfAKindRoll = findRollThatOccurredNTimes(rollCounts, 4);
        return fourOfAKindRoll * 4;
    }

    public static int calcSmallStraightScore(ArrayList<Die> dice) {
        Collections.sort(dice);
        for (int i = 0; i < dice.size(); ++i){
            int currentRoll = dice.get(i).getValue();
            int nextValueInStraight = i + 1;
            if (currentRoll != nextValueInStraight){
                return 0;
            }
        }
        return 15;
    }

    public static int calcLargeStraightScore(ArrayList<Die> dice) {
        Collections.sort(dice);
        for (int i = 0; i < dice.size(); ++i){
            int currentRoll = dice.get(i).getValue();
            int nextValueInStraight = i + 2;
            if (currentRoll != nextValueInStraight){
                return 0;
            }
        }
        return 20;
    }

    public static int calcFullHouseScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = Yatzy.getRollCounts(dice);
        int score = 0;

        for(int roll : rollCounts.keySet()) {
            int numberOfTimesSeenRoll = rollCounts.get(roll);

            if(numberOfTimesSeenRoll == 3) {
                score += roll * 3;
            }
            else if(numberOfTimesSeenRoll == 2) {
                score += roll * 2;
            }
            else {
                return 0;
            }
        }
        return score;
    }

    private static Hashtable<Integer, Integer> getRollCounts(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = new Hashtable<>();
        for(Die die : dice) {
            int currentRoll = die.getValue();
            int numberOfTimesSeen = rollCounts.getOrDefault(currentRoll, 0) + 1;
            rollCounts.put(currentRoll, numberOfTimesSeen);
        }
        return rollCounts;
    }

    private static int findRollThatOccurredNTimes(Hashtable<Integer, Integer> rollCounts, int occurrences) {
        for(int roll : rollCounts.keySet()) {
            int numberOfTimesSeen = rollCounts.get(roll);
            if(numberOfTimesSeen >= occurrences) {
                return roll;
            }
        }
        return 0;
    }
}
