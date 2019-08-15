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
    public void Given_Dice11336AndChanceCategory_When_CalculatingScore_Then_Return14() {
        ArrayList<Die> dice = setupDice(1,1,3,3,6);
        int score = Yatzy.calcScore(dice, "chance");
        assertEquals(14, score);
    }

    // Yatzy
    @Test
    public void Given_Dice11111AndYatzyCategory_When_CalculatingYatzyScore_Then_Return50() {
        ArrayList<Die> dice = setupDice(1,1,1,1,1);
        int score = Yatzy.calcScore(dice, "yatzy");
        assertEquals(50, score);
    }

    @Test
    public void Given_Dice11121AndYatzyCategory_When_CalculatingYatzyScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,1,1,2,1);
        int score = Yatzy.calcScore(dice, "yatzy");
        assertEquals(0, score);
    }

    // Numbers Section
    @Test
    public void Given_Dice12345AndOnesCategory_When_CalculatingScore_Then_Return1() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "ones");
        assertEquals(1, score);
    }

    @Test
    public void Given_Dice22345AndOnesCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(2,2,3,4,5);
        int score = Yatzy.calcScore(dice, "ones");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice12345AndTwosCategory_When_CalculatingScore_Then_Return2() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "twos");
        assertEquals(2, score);
    }

    @Test
    public void Given_Dice11345AndTwosCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,1,3,4,5);
        int score = Yatzy.calcScore(dice, "twos");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice12345AndThreesCategory_When_CalculatingScore_Then_Return3() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "threes");
        assertEquals(3, score);
    }

    @Test
    public void Given_Dice12445AndThreesCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,4,4,5);
        int score = Yatzy.calcScore(dice, "threes");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice12345AndFoursCategory_When_CalculatingScore_Then_Return4() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "fours");
        assertEquals(4, score);
    }

    @Test
    public void Given_Dice12355AndFoursCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,5,5);
        int score = Yatzy.calcScore(dice, "fours");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice12345AndFivesCategory_When_CalculatingScore_Then_Return5() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "fives");
        assertEquals(5, score);
    }

    @Test
    public void Given_Dice12346AndFivesCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,6);
        int score = Yatzy.calcScore(dice, "fives");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice12346AndSixesCategory_When_CalculatingScore_Then_Return6() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,6);
        int score = Yatzy.calcScore(dice, "sixes");
        assertEquals(6, score);
    }

    @Test
    public void Given_Dice12345AndSixesCategory_When_CalculatingScore_Then_Return0() throws InvalidCategoryException {
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score = Yatzy.calcScore(dice, "sixes");
        assertEquals(0, score);
    }

    // Pairs
    @Test
    public void Given_Dice33344AndPairCategory_When_CalculatingScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(3,3,3,4,4);
        int score = Yatzy.calcScore(dice, "pair");
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice43433AndPairCategory_When_CalculatingScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(4,3,4,3,3);
        int score = Yatzy.calcScore(dice, "pair");
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice13625AndPairCategory_When_CalculatingScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,3,6,2,5);
        int score = Yatzy.calcScore(dice, "pair");
        assertEquals(0, score);
    }

    // 2 Pair
    @Test
    public void Given_Dice11233AndTwoPairCategory_When_CalculatingScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(1,1,2,3,3);
        int score = Yatzy.calcScore(dice, "two pair");
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice11234AndTwoPairCategory_When_CalculatingScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(1,1,2,3,4);
        int score = Yatzy.calcScore(dice, "two pair");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice11222AndTwoPairCategory_When_CalculatingScore_Then_Return6() {
        ArrayList<Die> dice = setupDice(1,1,2,2,2);
        int score = Yatzy.calcScore(dice, "two pair");
        assertEquals(6, score);
    }

    // 3 of a kind
    @Test
    public void Given_Dice33345AndThreeOfAKindCategory_When_CalculatingScore_Then_Return9() {
        ArrayList<Die> dice = setupDice(3,3,3,4,5);
        int score = Yatzy.calcScore(dice, "three of a kind");
        assertEquals(9, score);
    }

    @Test
    public void Given_Dice33456AndThreeOfAKindCategory_When_CalculatingScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(3,3,4,5,6);
        int score = Yatzy.calcScore(dice, "three of a kind");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice33331AndThreeOfAKindCategory_When_CalculatingScore_Then_Return9() {
        ArrayList<Die> dice = setupDice(3,3,3,3,1);
        int score = Yatzy.calcScore(dice, "three of a kind");
        assertEquals(9, score);
    }

    // 4 of a kind
    @Test
    public void Given_Dice22225AndFourOfAKindCategory_When_CalculatingScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(2,2,2,2,5);
        int score = Yatzy.calcScore(dice, "four of a kind");
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice22255AndFourOfAKindCategory_When_CalculatingScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(2,2,2,5,5);
        int score = Yatzy.calcScore(dice, "four of a kind");
        assertEquals(0, score);
    }

    @Test
    public void Given_Dice22222AndFourOfAKindCategory_When_CalculatingScore_Then_Return8() {
        ArrayList<Die> dice = setupDice(2,2,2,2,2);
        int score = Yatzy.calcScore(dice, "four of a kind");
        assertEquals(8, score);
    }

    // Small Straight
    @Test
    public void Given_Dice12345AndSmallStraightCategory_When_CalculatingScore_Then_Return15(){
        ArrayList<Die> dice = setupDice(1,2,3,4,5);
        int score =  Yatzy.calcScore(dice, "small straight");
        assertEquals(15, score);
    }

    @Test
    public void Given_Dice11345AndSmallStraightCategory_When_CalculatingScore_Then_Return0(){
        ArrayList<Die> dice = setupDice(1,1,3,4,5);
        int score =  Yatzy.calcScore(dice, "small straight");
        assertEquals(0, score);
    }

    // Large Straight
    @Test
    public void Given_Dice23456AndLargeStraightCategory_When_CalculatingScore_Then_Return20(){
        ArrayList<Die> dice = setupDice(2,3,4,5,6);
        int score =  Yatzy.calcScore(dice, "large straight");
        assertEquals(20, score);
    }

    @Test
    public void Given_Dice23454AndLargeStraightCategory_When_CalculatingScore_Then_Return0(){
        ArrayList<Die> dice = setupDice(2,3,4,5,4);
        int score =  Yatzy.calcScore(dice, "large straight");
        assertEquals(0, score);
    }

    // Full House
    @Test
    public void Given_Dice11222AndFullHouseCategory_When_CalculatingScore_Then_Return8(){
        ArrayList<Die> dice = setupDice(1,1,2,2,2);
        int score = Yatzy.calcScore(dice, "full house");
        assertEquals(8, score);
    }

    @Test
    public void Given_Dice22334AndFullHouseCategory_When_CalculatingScore_Then_Return0() {
        ArrayList<Die> dice = setupDice(2,2,3,3,4);
        int score = Yatzy.calcScore(dice, "full house");
        assertEquals(0, score);
    }
}