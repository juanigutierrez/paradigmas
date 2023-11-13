package linea;

public class ModeA extends ModeManager {
    private char style = 'A';
    public boolean checkestilo(char estilo){
        return (estilo == this.style);
    }
    public boolean finished(Linea linea) {
        return (linea.checkTalesDirectionMatches(0,1) || linea.checkTalesDirectionMatches(1,0) || linea.draw());
    }
}
