import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Yatzy {

    public static int calcScore(ArrayList<Die> dice, Category category) {
        switch (category) {
            case CHANCE:
                return Yatzy.calcChanceScore(dice);
            case YATZY:
                return Yatzy.calcYatzyScore(dice);
            case ONES:
                return Yatzy.calcSingleNumberScore(1, dice);
            case TWOS:
                return Yatzy.calcSingleNumberScore(2, dice);
            case THREES:
                return Yatzy.calcSingleNumberScore(3, dice);
            case FOURS:
                return Yatzy.calcSingleNumberScore(4, dice);
            case FIVES:
                return Yatzy.calcSingleNumberScore(5, dice);
            case SIXES:
                return Yatzy.calcSingleNumberScore(6, dice);
            case PAIR:
                return Yatzy.calcNOfAKindScore(dice, 2);
            case TWO_PAIR:
                return Yatzy.calcTwoPairScore(dice);
            case THREE_OF_A_KIND:
                return Yatzy.calcNOfAKindScore(dice, 3);
            case FOUR_OF_A_KIND:
                return Yatzy.calcNOfAKindScore(dice, 4);
            case SMALL_STRAIGHT:
                return Yatzy.calcStraightScore(dice, true);
            case LARGE_STRAIGHT:
                return Yatzy.calcStraightScore(dice, false);
            case FULL_HOUSE:
                return Yatzy.calcFullHouseScore(dice);
            default:
                return 0;
        }
    }

    private static int calcChanceScore(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            int currentRoll = die.getValue();
            score += currentRoll;
        }
        return score;
    }

    private static int calcYatzyScore(ArrayList<Die> dice) {
        final int YATZY_SCORE = 50;
        int firstDieRoll = dice.get(0).getValue();
        for (Die die : dice) {
            int currentRoll = die.getValue();
            if (currentRoll != firstDieRoll) {
                return 0;
            }
        }
        return YATZY_SCORE;
    }

    private static int calcSingleNumberScore(int number, ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            int currentRoll = die.getValue();
            if (currentRoll == number) {
                score += number;
            }
        }
        return score;
    }

    private static int calcTwoPairScore(ArrayList<Die> dice) {
        Hashtable<Die, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        int numberOfPairsSeen = 0;
        int score = 0;
        ArrayList<Die> sortedUniqueDice = Yatzy.sortUniqueRollsInDescendingOrder(numberOfTimesEachRollOccurred);

        for (Die die : sortedUniqueDice) {
            int roll = die.getValue();
            int numberOfTimesSeen = numberOfTimesEachRollOccurred.get(die);
            if (numberOfTimesSeen >= 2) {
                numberOfPairsSeen++;
                score += roll * 2;
            }
        }
        return numberOfPairsSeen == 2 ? score : 0;
    }

    private static int calcNOfAKindScore(ArrayList<Die> dice, int occurrences) {
        Hashtable<Die, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        Die nOfAKindDie = Yatzy.findHighestDieThatOccurredNOrMoreTimes(numberOfTimesEachRollOccurred, occurrences);
        if (nOfAKindDie != null) {
            return nOfAKindDie.getValue() * occurrences;
        }
        else {
            return 0;
        }
    }

    private static int calcStraightScore(ArrayList<Die> dice, boolean calcSmallStraight) {
        final int SMALL_STRAIGHT_SCORE = 15;
        final int LARGE_STRAIGHT_SCORE = 20;

        Collections.sort(dice);
        for (int i = 0; i < dice.size(); ++i) {
            int currentRoll = dice.get(i).getValue();
            int nextValueInStraight = calcSmallStraight ? i + 1 : i + 2;
            if (currentRoll != nextValueInStraight) {
                return 0;
            }
        }
        return calcSmallStraight ? SMALL_STRAIGHT_SCORE : LARGE_STRAIGHT_SCORE;
    }

    private static int calcFullHouseScore(ArrayList<Die> dice) {
        Hashtable<Die, Integer> numberOfTimesEachRollOccurred = Yatzy.findNumberOfTimesEachRollOccurred(dice);
        ArrayList<Die> sortedUniqueDice = Yatzy.sortUniqueRollsInDescendingOrder(numberOfTimesEachRollOccurred);
        int score = 0;
        for (Die die : sortedUniqueDice) {
            int roll = die.getValue();
            int numberOfTimesSeenRoll = numberOfTimesEachRollOccurred.get(die);

            if (numberOfTimesSeenRoll == 3) {
                score += roll * 3;
            }
            else if (numberOfTimesSeenRoll == 2) {
                score += roll * 2;
            }
            else {
                return 0;
            }
        }
        return score;
    }

    private static Hashtable<Die, Integer> findNumberOfTimesEachRollOccurred(ArrayList<Die> dice) {
        Hashtable<Die, Integer> rollCounts = new Hashtable<>();
        for (Die die : dice) {
            int numberOfTimesSeen = rollCounts.getOrDefault(die, 0) + 1;
            rollCounts.put(die, numberOfTimesSeen);
        }
        return rollCounts;
    }

    private static Die findHighestDieThatOccurredNOrMoreTimes(Hashtable<Die, Integer> numberOfTimesEachRollOccurred, int occurrences) {
        ArrayList<Die> sortedUniqueDice = Yatzy.sortUniqueRollsInDescendingOrder(numberOfTimesEachRollOccurred);
        for (Die die : sortedUniqueDice) {
            int numberOfTimesSeen = numberOfTimesEachRollOccurred.get(die);
            if (numberOfTimesSeen >= occurrences) {
                return die;
            }
        }
        return null;
    }

    private static ArrayList<Die> sortUniqueRollsInDescendingOrder(Hashtable<Die, Integer> numberOfTimesEachRollOccurred) {
        ArrayList<Die> rolls = new ArrayList<>(numberOfTimesEachRollOccurred.keySet());
        Collections.sort(rolls);
        Collections.reverse(rolls);
        return rolls;
    }
}
