import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class YatzyTest {
    private ArrayList<Die> setupDice(int d1, int d2, int d3, int d4, int d5) {
        ArrayList<Die> dice = new ArrayList<>();
        dice.add(new Die(d1));
        dice.add(new Die(d2));
        dice.add(new Die(d3));
        dice.add(new Die(d4));
        dice.add(new Die(d5));
        return dice;
    }

    // Chance
    @Test
    public void Given_Dice11336_When_CalculatingChanceScore_Then_Return14() {
        ArrayList<Die> dice = setupDice(1,1,3,3,6);
        assertEquals(14, Yatzy.calcChanceScore(dice));
    }

    // Yatzy
    @Test
    public void Given_Dice11111_When_CalculatingYatzyScore_Then_Return50() {
        ArrayList<Die> dice = setupDice(1,1,1,1,1);
        assertEquals(50, Yatzy.calcYatzyScore(dice));
    }

    @Test
    public void Given_Dice11121_When_CalculatingYatzyScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,1,1,2,1);
        assertNotEquals(50, Yatzy.calcYatzyScore(dice));
    }

    // Upper Section
    @Test
    public void Given_Dice11121_When_CalculatingOnesScore_Then_Return4() {
        ArrayList<Die> dice = setupDice(1,1,1,2,1);
        assertEquals(4, Yatzy.calcSingleNumberScore(1, dice));
    }

    @Test
    public void Given_Dice11121_When_CalculatingFoursScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,1,1,2,1);
        assertEquals(0, Yatzy.calcSingleNumberScore(4, dice));
    }

    // Pairs
    @Test
    public void Given_Dice33344_When_CalculatingPairScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(3,3,3,4,4);
        assertEquals(8, Yatzy.calcPairScore(dice));
    }

    @Test
    public void Given_Dice11626_When_CalculatingPairScore_Then_Return12() {
        ArrayList<Die> dice = setupDice(1,1,6,2,6);
        assertEquals(12, Yatzy.calcPairScore(dice));
    }

    // 2 Pair
    @Test
    public void Given_Dice11233_When_CalculatingTwoPairScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(1,1,2,3,3);
        assertEquals(8, Yatzy.calcTwoPairScore(dice));
    }

    @Test
    public void Given_Dice11234_When_CalculatingTwoPairScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,1,2,3,4);
        assertEquals(0, Yatzy.calcTwoPairScore(dice));
    }

    @Test
    public void Given_Dice11222_When_CalculatingTwoPairScore_Then_Return6() {
        ArrayList<Die> dice = setupDice(1,1,2,2,2);
        assertEquals(6, Yatzy.calcTwoPairScore(dice));
    }

    // 3 of a kind
    @Test
    public void Given_Dice33345_When_CalculatingThreeOfAKindScore_Then_Return9() {
        ArrayList<Die> dice = setupDice(3,3,3,4,5);
        assertEquals(9, Yatzy.calcThreeOfAKindScore(dice));
    }

    @Test
    public void Given_Dice33456_When_CalculatingThreeOfAKindScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(3,3,4,5,6);
        assertEquals(0, Yatzy.calcThreeOfAKindScore(dice));
    }

    @Test
    public void Given_Dice33331_When_CalculatingThreeOfAKindScore_Then_Return9() {
        ArrayList<Die> dice = setupDice(3,3,3,3,1);
        assertEquals(9, Yatzy.calcThreeOfAKindScore(dice));
    }

    // 4 of a kind
    @Test
    public void Given_Dice22225_When_CalculatingFourOfAKindScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(2,2,2,2,5);
        assertEquals(8, Yatzy.calcFourOfAKindScore(dice));
    }

    @Test
    public void Given_Dice22255_When_CalculatingFourOfAKindScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(2,2,2,5,5);
        assertEquals(0, Yatzy.calcFourOfAKindScore(dice));
    }

    @Test
    public void Given_Dice22222_When_CalculatingFourOfAKindScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(2,2,2,2,2);
        assertEquals(8, Yatzy.calcFourOfAKindScore(dice));
    }

    // Small Straight
    @Test
    public void Given_Dice12345_When_CalculatingSmallStraightScore_Then_Return15(){
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score =  Yatzy.calcSmallStraightScore(dice);
        assertEquals(15, score);
    }

    @Test
    public void Given_Dice11345_When_CalculatingSmallStraightScore_Then_Return0(){
        ArrayList<Die> dice = setupDice(1,1,3,4,5);
        int score =  Yatzy.calcSmallStraightScore(dice);
        assertEquals(0, score);
    }

    // Large Straight
    @Test
    public void Given_Dice23456_When_CalculatingLargeStraightScore_Then_Return20(){
        ArrayList<Die> dice = setupDice(2,3,4,5,6);
        int score =  Yatzy.calcLargeStraightScore(dice);
        assertEquals(20, score);
    }

    @Test
    public void Given_Dice23454_When_CalculatingLargeStraightScore_Then_Return0(){
        ArrayList<Die> dice = setupDice(2,3,4,5,4);
        int score =  Yatzy.calcLargeStraightScore(dice);
        assertEquals(0, score);
    }

    // Full House
    @Test
    public void Given_Dice11222_When_CalculatingFullHouseScore_Then_Return8(){
        ArrayList<Die> dice = setupDice(1,1,2,2,2);
        int score = Yatzy.calcFullHouseScore(dice);
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice22334_When_CalculatingFullHouseScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(2,2,3,3,4);
        int score = Yatzy.calcFullHouseScore(dice);
        assertEquals(0, score);
    }

    // Select Category
    @Test
    public void Given_Dice11311AndCategoryChance_When_CalculatingScore_Then_Return7() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,1,3,1,1);
        int score = Yatzy.calcScore(dice, "chance");
        assertEquals(7, score);
    }

    @Test
    public void Given_Dice11111AndCategoryYatzy_When_CalculatingScore_Then_Return50() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,1,1,1,1);
        int score = Yatzy.calcScore(dice, "yatzy");
        assertEquals(50, score);
    }

    @Test
    public void Given_Dice12345AndCategoryOnes_When_CalculatingScore_Then_Return1() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "ones");
        assertEquals(1, score);
    }

    @Test
    public void Given_Dice12345AndCategoryTwos_When_CalculatingScore_Then_Return2() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "twos");
        assertEquals(2, score);
    }

    @Test
    public void Given_Dice12345AndCategoryThrees_When_CalculatingScore_Then_Return3() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "threes");
        assertEquals(3, score);
    }

    @Test
    public void Given_Dice12345AndCategoryFours_When_CalculatingScore_Then_Return4() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "fours");
        assertEquals(4, score);
    }

    @Test
    public void Given_Dice12345AndCategoryFives_When_CalculatingScore_Then_Return5() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "fives");
        assertEquals(5, score);
    }

    @Test
    public void Given_Dice12346AndCategorySixes_When_CalculatingScore_Then_Return6() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,6);
        int score = Yatzy.calcScore(dice, "sixes");
        assertEquals(6, score);
    }

    @Test
    public void Given_Dice12332AndCategoryPair_When_CalculatingScore_Then_Return6() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,3,2);
        int score = Yatzy.calcScore(dice, "pair");
        assertEquals(6, score);
    }

    @Test
    public void Given_Dice12332AndCategoryTwoPair_When_CalculatingScore_Then_Return10() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,3,2);
        int score = Yatzy.calcScore(dice, "2 pair");
        assertEquals(10, score);
    }

    @Test
    public void Given_Dice32332AndCategoryThreeOfAKind_When_CalculatingScore_Then_Return9() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(3,2,3,3,2);
        int score = Yatzy.calcScore(dice, "3 of a kind");
        assertEquals(9, score);
    }

    @Test
    public void Given_Dice32333AndCategoryFourOfAKind_When_CalculatingScore_Then_Return12() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(3,2,3,3,3);
        int score = Yatzy.calcScore(dice, "4 of a kind");
        assertEquals(12, score);
    }

    @Test
    public void Given_Dice12345AndCategorySmallStraight_When_CalculatingScore_Then_Return15() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "small straight");
        assertEquals(15, score);
    }

    @Test
    public void Given_Dice23456AndCategoryLargeStraight_When_CalculatingScore_Then_Return15() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(2,3,4,5,6);
        int score = Yatzy.calcScore(dice, "large straight");
        assertEquals(20, score);
    }

    @Test
    public void Given_Dice11222AndCategoryFullHouse_When_CalculatingScore_Then_Return8() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,1,2,2,2);
        int score = Yatzy.calcScore(dice, "full house");
        assertEquals(8, score);
    }
}