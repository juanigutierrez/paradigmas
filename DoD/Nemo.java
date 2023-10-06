package DoD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Nemo {
    private final List<String> cardinals_points = new ArrayList<>(List.of("n", "e", "s", "o"));
    private int coordinateX;
    private int coordinateY;
    private final Direction direction;
    private final Depth depth;
    private final Capsule capsule;
    public Nemo(){
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.depth = new Depth();
        this.direction = new Direction();
        this.capsule = new Capsule();
    }
    public Nemo readofcomand(String command){
        Arrays.stream(command.split("")).forEach(commando -> control(commando));
        return this;
    }
    public Void control(String command) {

        if (Objects.equals(command, "d")) {
            depth.downDepth();
        } else if (Objects.equals(command, "u") && depth.getDepth() > 0) {
            depth.upDepth();
        } else if (Objects.equals(command, "r")) {
            direction.toRightDirection();
        } else if (Objects.equals(command, "l")) {
            direction.toLeftDirection();
        } else if (Objects.equals(command, "f")) {

            if (direction.getDirection() % 2 == 0) {
                coordinateY += 1;
            }
            else {
                coordinateX -= 1;
            }
        } else if (Objects.equals(command, "m")){
            if (depth.getDepth() <= 1){
                capsule.freeCapsule();
            }
            else{
                throw new RuntimeException("el sumergible estallo");

            }
        }
        return null;
    }
    public Integer getcoordenateX(){
        return coordinateX;
    }

    public Integer getcoordenateY(){
        return coordinateY;
    }

    public int getNemoDepth(){
        return depth.getDepth();
    }
    public String getNemoDirection() {
        return cardinals_points.get(direction.getDirection());
    }
    public boolean getNemoCapsule(){
        return capsule.getCapsule();
    }
}
//el polimorfismo va a estar en como implementen do cada comando