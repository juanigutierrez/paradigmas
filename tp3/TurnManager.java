package linea;

public abstract class TurnManager {
    protected static String  InvalidTurn= "It is not your turn";

    public abstract TurnManager nextTurno();
    public abstract void playRedAt (Linea linea, int x );
    public abstract void playBlueAt (Linea linea, int x );
    public abstract String getTurno();


}
