package linea;

import java.util.List;

public abstract class ModeManager {
    public static String invalidMode = "Invalid Mode";
    private static List<linea.ModeManager> estilos = List.of(new ModeA(),new ModeB(),new ModeC());
    public abstract boolean finished(Linea linea);
    public abstract boolean checkestilo(char estilo);

    public static ModeManager getestilo(char estilo){
        return estilos.stream().filter(each -> each.checkestilo(estilo)).findAny().orElseThrow(() -> new RuntimeException(invalidMode));
    }
}
