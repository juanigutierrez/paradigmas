package DoD;

public class West extends Direction {

    public void move(Nemo nemo) {
        nemo.movewest();
    }
    public Direction turnleft(){
        return new South();
    }
    public Direction turnright(){
        return new North();
    }
}
