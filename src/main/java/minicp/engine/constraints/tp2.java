package minicp.engine.constraints;

import minicp.engine.core.AbstractConstraint;
import minicp.engine.core.IntVar;

public class tp2 extends AbstractConstraint {

    private final IntVar[] y;
    private final IntVar x, z;

    public tp2(IntVar x, IntVar[] y, IntVar z) {
        super(y[0].getSolver());
        this.y = y;
        this.x = x;
        this.z = z;
    }

    @Override
    public void post() {
        z.removeBelow(0);
        x.propagateOnBoundChange(this);
        for (IntVar yi : y) yi.propagateOnBoundChange(this);
        z.propagateOnBoundChange(this);
        propagate();
    }

    @Override
    public void propagate() {
        z.removeBelow(0);

        double meanMax = 0;
        for (IntVar yi : y) {
            meanMax += yi.max();
        }
        meanMax /= y.length;

        if (x.isFixed() && x.min() == 0) {
            z.removeAbove((int) Math.ceil(meanMax / 2.0));
        } else if (x.isFixed()) {
            z.removeAbove((int) Math.ceil(meanMax));
        }
    }

    public static tp2 makeTp2(IntVar x, IntVar[] y, IntVar z) {
        return new tp2(x, y, z);
    }
}
