package minicp.engine.constraints;


import minicp.engine.core.AbstractConstraint;
import minicp.engine.core.IntVar;
import minicp.util.exception.InconsistencyException;
import minicp.util.exception.NotImplementedException;
public class tp2 extends AbstractConstraint {
    private final IntVar[] y;
    private final IntVar x, z;

     /**
     * Creates a constraint such
     * that z ≥ 0
     * si x = 0 alors moyenne(y[0], ..., y[n − 1]) ≤ 2 ∗ z sinon moyenne(y[0], ..., y[n − 1]) ≤ z
     * @param x verify if equals to 0
     * @param y the variable
     * @param v the right member
     * @see 
     */
    public tp2(IntVar x, IntVar[] y, IntVar z) { 
        super(y[0].getSolver());
        this.y = y;
        this.x = x;
        this.z = z;
       
    }

    @Override
    public void post() {
    }

    @Override
    public void propagate() {
    }
}
