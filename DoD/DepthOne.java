package DoD;
import java.util.List;

public class DepthOne extends Depth {
    public List<Depth> upDepth(List<Depth> depths) {
        depths.remove(this);
        return depths;
    }
    public List<Depth> downDepth(List<Depth> depths) {
        depths.add(new DepthBellowOne());
        return depths;
    }
    public void permitFreeCapsule() {

    }
}