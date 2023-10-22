package DoD;
import java.util.ArrayList;
import java.util.List;

public class Nemo {

    public List <Depth> depth_list = new ArrayList<>(new ArrayList<>(List.of(new DepthZero())));
    private Coordinates coordinates;
    private Direction direction;

    public Nemo(Coordinates coordinates , Direction direction){
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Nemo readofcommand(String commands) {
        commands.chars().forEach(c -> CommandManager.checkCommands((char) c).execute(this));
        return this;
    }

    //----CHANGES DIRECTION----
    public void moveForward(){
        getNemoDirection().move(this);
    }
    public void toRight() {
        direction = direction.turnright();
    }
    public void toLeft() {
        direction = direction.turnleft();
    }
    public void freecapsule(){
        depth_list.get(depth_list.size()-1).permitFreeCapsule();
    }
    public void DownDepth(){
        depth_list = depth_list.get(depth_list.size()-1).downDepth(depth_list);
    }
    public void UpDepth(){
        depth_list = depth_list.get(depth_list.size()-1).upDepth(depth_list);
    }

    //----MOVES----
    public void moveeast() {
        Coordinates move = new Coordinates(1, 0);
        coordinates = coordinates.add(move);
    }
    public void movewest() {
        Coordinates move = new Coordinates(-1, 0);
        coordinates = coordinates.add(move);
    }
    public void movenorth() {
        Coordinates move = new Coordinates(0, 1);
        coordinates = coordinates.add(move);
    }
    public void movesouth() {
        Coordinates move = new Coordinates(0, -1);
        coordinates = coordinates.add(move);
    }

    //----GETS----
    public int getNemoDepth(){
        return depth_list.size()-1;
    }
    public Direction getNemoDirection() {
        return direction;
    }
    public Coordinates getNemoCoordinates() {
        return coordinates;
    }

}


