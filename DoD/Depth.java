package DoD;
public class Depth {
    private int depth;
    public Depth() {
        this.depth = 0;
    }

    //acá voy a decidir con que comando se hace que cosa

    public void upDepth() {
        depth -= 1;
    }
    public void downDepth() {
        depth += 1;
    }
    public int getDepth() {
        return depth;
    }
}
