package DoD;

public class East extends Direction {
    public void move(Nemo nemo) {
        nemo.moveeast();
    }
    public Direction turnleft(){
        return new North();
    }
    public Direction turnright(){
        return new South();
    }
}
