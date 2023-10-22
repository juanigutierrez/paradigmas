package DoD;

public class FreeCapsule extends CommandManager {
    private char command = 'm';

    public Boolean checkCommandLetter(char c) {
        return c == command;
    }

    public void execute(Nemo nemo) {
        nemo.freecapsule();
    }
}
