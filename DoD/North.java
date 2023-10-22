package DoD;

public class North extends Direction {
    public void move(Nemo nemo) {
        nemo.movenorth();
    }

    public Direction turnleft(){
        return new West();
    }
    public Direction turnright(){
        return new East();
    }
}