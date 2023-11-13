package linea;

public class NothingTurn extends TurnManager {
    public static String gameIsOver = "The Game Finished";
    public String getTurno(){
        throw new RuntimeException(gameIsOver);
    }
    public void playRedAt (Linea linea, int x ){
        throw new RuntimeException(gameIsOver);
    }
    public void playBlueAt (Linea linea, int x ){
        throw new RuntimeException(gameIsOver);
    }
    public TurnManager nextTurno(){
        throw new RuntimeException(gameIsOver);
    }

}
