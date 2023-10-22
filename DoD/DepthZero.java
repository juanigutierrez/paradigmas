package DoD;

import java.util.List;

public class DepthZero extends Depth {
    public List<Depth> upDepth(List<Depth> depths) {
        return depths;
    }
    public List<Depth> downDepth(List<Depth> depths) {
        depths.add(new DepthOne());
        return depths;
    }
    public void permitFreeCapsule() {

    }
}