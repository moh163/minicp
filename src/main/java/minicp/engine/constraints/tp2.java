package minicp.engine.constraints;


import minicp.engine.core.AbstractConstraint;
import minicp.engine.core.IntVar;
import minicp.util.exception.NotImplementedException;
public class tp2 extends AbstractConstraint {
    private final IntVar y;
    private final int x, z;

     /**
     * Creates a constraint such
     * that {@code x != y + v}
     * @param x the left member
     * @param y the right memer
     * @param v the offset value on y
     * @see 
     */
    public tp2(int x, IntVar y, int v) { 
        super(y.getSolver());
        this.y = y;
        this.x = x;
        this.z = v;
       
    }

    @Override
    public void post() {
    }

    @Override
    public void propagate() {
    }
}
