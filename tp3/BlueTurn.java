package linea;
public class BlueTurn extends TurnManager {
    private String color = "b";
    public String getTurno(){
        return color;
    }
    public void playRedAt (Linea linea, int x ){
        throw new RuntimeException(TurnManager.InvalidTurn);
    }

    public void playBlueAt (Linea linea, int x ){
        linea.playInMe(x,color);
    }
    public TurnManager nextTurno(){
        if (Linea.isFinished()){
           return new NothingTurn();
        }
        return new RedTurn();
    }

}