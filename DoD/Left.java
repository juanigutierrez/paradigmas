package DoD;

public class Left extends CommandManager {
    private char command = 'l';

    public Boolean checkCommandLetter(char c) {
        return c == command;
    }

    public void execute(Nemo nemo) {
        nemo.toLeft();
    }
}
