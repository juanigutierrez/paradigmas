package DoD;

import java.util.List;

public abstract class Depth {
    public abstract List<Depth> upDepth(List<Depth> depths);
    public abstract List<Depth> downDepth(List<Depth> depths);
    public abstract void permitFreeCapsule();

    }

