package DoD;

public class Forward extends CommandManager {
    private char command = 'f';

    public Boolean checkCommandLetter(char c) {
        return c == command;
    }

    public void execute(Nemo nemo) {
        nemo.moveForward();
    }
}
