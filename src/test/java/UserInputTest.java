import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserInputTest {
    @Test
    public void Given_TextInputOfDice11126_When_ProcessingInput_Then_ReturnListOfDiceWithValues11122() {
        String input = "1,1,1,2,6";
        ArrayList<Die> expected = YatzyTest.setupDice(1, 1, 1, 2, 6);
        ArrayList<Die> dice = UserInput.processRollsInput(input);
        assertEquals(expected, dice);
    }

    @Test(expected = InvalidInputException.class)
    public void Given_TextInputOfDice111222_When_ProcessingInput_Then_ThrowInvalidInputException() {
        String input = "1,1,1,2,2,2";
        UserInput.processRollsInput(input);
    }

    @Test(expected = InvalidInputException.class)
    public void Given_TextInputOfDice12_When_ProcessingInput_Then_ThrowInvalidInputException() {
        String input = "1,2";
        UserInput.processRollsInput(input);
    }

    @Test(expected = InvalidInputException.class)
    public void Given_TextInputOfDice17122_When_ProcessingInput_Then_ThrowInvalidInputException() {
        String input = "1,7,1,2,2";
        UserInput.processRollsInput(input);
    }

    @Test(expected = InvalidInputException.class)
    public void Given_TextInputOfDice10122_When_ProcessingInput_Then_ThrowInvalidInputException() {
        String input = "1,0,1,2,2";
        UserInput.processRollsInput(input);
    }

    @Test
    public void Given_TextInputOfOnes_When_ProcessingInput_Then_ReturnOnesCategory() {
        String input = "ones";
        Category category = UserInput.processCategoryInput(input);
        assertEquals(Category.ONES, category);
    }

    @Test
    public void Given_TextInputOfSmallStraight_When_ProcessingInput_Then_ReturnSmallStraightCategory() {
        String input = "small straight";
        Category category = UserInput.processCategoryInput(input);
        assertEquals(Category.SMALL_STRAIGHT, category);
    }

    @Test(expected = InvalidCategoryException.class)
    public void Given_TextInputOfAsdf_When_ProcessingInput_Then_ThrowInvalidCategoryException() {
        String input = "Asdf";
        UserInput.processCategoryInput(input);
    }
}