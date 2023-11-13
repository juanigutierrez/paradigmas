package linea;

public class ModeB extends ModeManager {
    private char style = 'B';
    public boolean checkestilo(char estilo){
        return (estilo == this.style);
    }
    public boolean finished(Linea linea) {
        return (linea.checkTalesDirectionMatches(1,1) || linea.checkTalesDirectionMatches(-1,1) || linea.draw());
    }
}
