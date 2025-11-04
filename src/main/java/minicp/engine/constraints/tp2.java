package minicp.engine.constraints;


import minicp.engine.core.AbstractConstraint;
import minicp.engine.core.IntVar;
import minicp.util.exception.InconsistencyException;
import minicp.util.exception.NotImplementedException;
public class tp2 extends AbstractConstraint {
    private final IntVar[] y;
    private final int x, z;

     /**
     * Creates a constraint such
     * that z ≥ 0
     * si x = 0 alors moyenne(y[0], ..., y[n − 1]) ≤ 2 ∗ z sinon moyenne(y[0], ..., y[n − 1]) ≤ z
     * @param x verify if equals to 0
     * @param y the variable
     * @param v the right member
     * @see 
     */
    public tp2(int x, IntVar[] y, int v) { 
        super(y[0].getSolver());
        this.y = y;
        this.x = x;
        this.z = v;
       
    }

    @Override
    public void post() {
         if (z < 0) {
            throw new InconsistencyException();
        }
    }

    @Override
    public void propagate() {
    }
}
