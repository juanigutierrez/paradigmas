package DoD;

public class Down extends CommandManager {
    private char command = 'd';

    public Boolean checkCommandLetter(char c) {
        return c == command;
    }

    public void execute(Nemo nemo) {
        nemo.DownDepth();
    }
}
