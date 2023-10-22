package DoD;

public class Up extends CommandManager {

    public Boolean checkCommandLetter(char c) {
        return c == 'u';
    }

    public void execute(Nemo nemo) {
        nemo.UpDepth();
    }
}
