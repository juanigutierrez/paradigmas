package DoD;

public class Direction {
    private int direction;



    public Direction() {
        this.direction = 0; // Por defecto, la dirección se inicializa en 0 (norte)
    }

//    public Object controlX (String command) {
//        if (Objects.equals(command, "r")) {
//            return direction.toRightDirection();
//        } else if (Objects.equals(command, "l")) {
//            direction.toLeftDirection();
//        }
//        return null;
//    }

    public void toRightDirection() {
        direction = (direction - 1 + 4) % 4;
    }
    public void toLeftDirection() {
        direction = (direction + 1) % 4;
    }
    public int getDirection() {
        return direction;
    }
}
