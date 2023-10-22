package DoD;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int[] getCoordinatesArray() {
        return new int[]{x, y};
    }
    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }


}
