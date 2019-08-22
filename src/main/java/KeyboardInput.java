import java.util.Scanner;

public class KeyboardInput implements IKeyboardInput {

    private Scanner keyboardInput = new Scanner(System.in);

    @Override
    public String nextLine() {
        return this.keyboardInput.nextLine();
    }
}
