package DoD;

public class South extends Direction {
    public void move(Nemo nemo) {
        nemo.movesouth();
    }

    public Direction turnleft(){
        return new East();
    }
    public Direction turnright(){
        return new West();
    }
}
