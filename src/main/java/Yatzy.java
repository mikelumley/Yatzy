import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Yatzy {

    private static final int YATZY_SCORE = 50;
    private static final int SMALL_STRAIGH_SCORE = 15;
    private static final int LARGE_STRAIGHT_SCORE = 20;

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
            if(currentRoll == number) {
                score += number;
            }
        }
        return score;
    }

    public static int calcPairScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int pairRoll = Yatzy.findRollThatOccurredNOrMoreTimes(numberOfTimesEachRollOccurred, 2);
        return pairRoll * 2;
    }

    public static int calcTwoPairScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int numberOfPairsSeen = 0;
        int score = 0;

        for(int roll : numberOfTimesEachRollOccurred.keySet()) {
            if(numberOfTimesEachRollOccurred.get(roll) >= 2) {
                numberOfPairsSeen++;
                score += roll * 2;
            }
        }
        return numberOfPairsSeen == 2 ? score : 0;
    }

    public static int calcThreeOfAKindScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int threeOfAKindRoll = Yatzy.findRollThatOccurredNOrMoreTimes(numberOfTimesEachRollOccurred, 3);
        return threeOfAKindRoll * 3;
    }

    public static int calcFourOfAKindScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int fourOfAKindRoll = Yatzy.findRollThatOccurredNOrMoreTimes(numberOfTimesEachRollOccurred, 4);
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
        return SMALL_STRAIGH_SCORE;
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
        return LARGE_STRAIGHT_SCORE;
    }

    public static int calcFullHouseScore(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int score = 0;

        for(int roll : numberOfTimesEachRollOccurred.keySet()) {
            int numberOfTimesSeenRoll = numberOfTimesEachRollOccurred.get(roll);

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

    private static Hashtable<Integer, Integer> findNumberOfTimesEachRollOccurred(ArrayList<Die> dice) {
        Hashtable<Integer, Integer> rollCounts = new Hashtable<>();
        for(Die die : dice) {
            int currentRoll = die.getValue();
            int numberOfTimesSeen = rollCounts.getOrDefault(currentRoll, 0) + 1;
            rollCounts.put(currentRoll, numberOfTimesSeen);
        }
        return rollCounts;
    }

    private static int findRollThatOccurredNOrMoreTimes(Hashtable<Integer, Integer> numberOfTimesEachRollOccurred, int occurrences) {
        for(int roll : numberOfTimesEachRollOccurred.keySet()) {
            int numberOfTimesSeen = numberOfTimesEachRollOccurred.get(roll);
            if(numberOfTimesSeen >= occurrences) {
                return roll;
            }
        }
        return 0;
    }
}
