package minicp.engine.constraints;

import minicp.engine.core.AbstractConstraint;
import minicp.engine.core.IntVar;

public class Tp2 extends AbstractConstraint {

    private final IntVar[] y;
    private final IntVar x, z;

    /**
     * Creates a constraint such
     * that z ≥ 0
     * si x = 0 alors moyenne(y[0], ..., y[n − 1]) ≤ 2 ∗ z sinon moyenne(y[0], ...,
     * y[n − 1]) ≤ z
     * 
     * @param x verify if equals to 0
     * @param y the variable
     * @param z bounding the mean
     */

    public Tp2(IntVar x, IntVar[] y, IntVar z) {
        super(y[0].getSolver());
        this.y = y;
        this.x = x;
        this.z = z;
    }

    @Override
    public void post() {
        z.removeBelow(0);
        x.propagateOnNotZero(this);
        x.propagateOnFix(this);
        for (IntVar yi : y)
            yi.propagateOnMinChange(this);
        z.propagateOnMaxChange(this);
        propagate();
    }

    @Override
    public void propagate() {
        if (!x.isFixed())
            return;
        double meanMin = 0;
        for (IntVar yi : y) {
            meanMin += yi.min();
        }
        meanMin /= y.length;

        //resserer les bornes inferieur de z
        if (x.min() == 0) {
            z.removeBelow((int) Math.ceil(meanMin / 2.0));
        } else {
            z.removeBelow((int) Math.ceil(meanMin));
        }

        //resserer borne superieur de y
        for (int i = 0; i < y.length; i++) {
            // somme min des autres y
            int sumMinOther = 0;
            for (int j = 0; j < y.length; j++) {
                if (j != i) {
                    sumMinOther += y[j].min();
                }
            }
            int yiMax;
            if (x.min() == 0) {
                yiMax = (int) Math.floor(y.length * 2 * z.max() - sumMinOther);
            } else {
                yiMax = (int) Math.floor(y.length * z.max() - sumMinOther);
            }
            y[i].removeAbove(yiMax);
        }
    }

}