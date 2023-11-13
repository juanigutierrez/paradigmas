package linea;
public class RedTurn extends TurnManager {
    private String color = "r";
    public String getTurno(){
        return color;
    }
    public void playRedAt (Linea linea, int x ){
        linea.playInMe(x,color);
    }
    public void playBlueAt (Linea linea, int x ){
        throw new RuntimeException(TurnManager.InvalidTurn);
    }

    public TurnManager nextTurno(){
        if (Linea.isFinished()){
           return new NothingTurn();
        }
        return new BlueTurn();
    }


}
