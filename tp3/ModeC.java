package linea;

public class ModeC extends ModeManager {
    private char style = 'C';
    public boolean checkestilo(char estilo){
        return (estilo == this.style);
    }

    public boolean finished(Linea linea) {
        return (new ModeA().finished(linea) || new ModeB().finished(linea));
    }
}
