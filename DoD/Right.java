package DoD;

public class Right extends CommandManager {
    private char command = 'r';

    public Boolean checkCommandLetter(char c) {
        return c == command;
    }

    public void execute(Nemo nemo) {
        nemo.toRight();
    }

}
