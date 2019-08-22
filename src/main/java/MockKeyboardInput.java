public class MockKeyboardInput implements IKeyboardInput {

    private int timesNextLineCalled = 0;
    private String inputToMock;
    private String correctInputToMock;

    public MockKeyboardInput(String correctInputToMock) {
        this.inputToMock = correctInputToMock;
    }

    public MockKeyboardInput(String wrongInputToMock, String correctInputToMock) {
        this.inputToMock = wrongInputToMock;
        this.correctInputToMock = correctInputToMock;
    }

    @Override
    public String nextLine() {
        timesNextLineCalled++;
        return timesNextLineCalled == 2 ? correctInputToMock : inputToMock;
    }
}
