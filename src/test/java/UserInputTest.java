import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserInputTest {
    @Test
    public void Given_TextInputOfDice11126_When_ProcessingInput_Then_ReturnListOfDiceWithValues11126() {
        IKeyboardInput mockKeyboardInput = new MockKeyboardInput("1,1,1,2,6");
        ArrayList<Die> expected = YatzyTest.setupDice(1, 1, 1, 2, 6);
        ArrayList<Die> dice = UserInput.askForDiceRolls(mockKeyboardInput);
        assertEquals(expected, dice);
    }

    @Test
    public void Given_TextInputOfDice11127Then11126_When_ProcessingInput_Then_ReturnListOfDiceWithValues11126() {
        IKeyboardInput mockKeyboardInput = new MockKeyboardInput("1,1,1,2,7", "1,1,1,2,6");
        ArrayList<Die> expected = YatzyTest.setupDice(1, 1, 1, 2, 6);
        ArrayList<Die> dice = UserInput.askForDiceRolls(mockKeyboardInput);
        assertEquals(expected, dice);
    }

    @Test
    public void Given_TextInputOfOnes_When_ProcessingInput_Then_ReturnOnesCategory() {
        IKeyboardInput mockKeyboardInput = new MockKeyboardInput("ones");
        Category category = UserInput.askForCategory(mockKeyboardInput);
        assertEquals(Category.ONES, category);
    }

    @Test
    public void Given_TextInputOfAsdfThenSmallStraight_When_ProcessingInput_Then_ReturnSmallStraightCategory() {
        IKeyboardInput mockKeyboardInput = new MockKeyboardInput("Asdf", "small straight");
        Category category = UserInput.askForCategory(mockKeyboardInput);
        assertEquals(Category.SMALL_STRAIGHT, category);
    }
}