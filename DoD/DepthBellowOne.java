package DoD;

import java.util.List;

public class DepthBellowOne extends Depth {

    public static  String ERROR_NEMO_EXPLODED = "Nemo has exploded after capsule realease";

    public List<Depth> upDepth(List<Depth> depths) {
        depths.remove(this);
        return depths;
    }

    public List<Depth> downDepth(List<Depth> depths) {
        depths.add(new DepthBellowOne());
        return depths;
    }

    public void permitFreeCapsule() {
        throw new RuntimeException(ERROR_NEMO_EXPLODED);
    }
}