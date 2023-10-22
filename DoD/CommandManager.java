package DoD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommandManager {
    public abstract Boolean checkCommandLetter(char c);
    public abstract void execute(Nemo nemo);
    public  static List<CommandManager> possibleCommands = new ArrayList<>(Arrays.asList(new Up(), new Down(), new Left(), new Right(), new Forward(), new FreeCapsule()));
    public static CommandManager checkCommands(char c){
        return possibleCommands.stream().filter(command -> command.checkCommandLetter(c)).findFirst().orElse(null);   }
}

