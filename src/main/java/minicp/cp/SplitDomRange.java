package minicp.cp;

import minicp.engine.core.IntVar;
import minicp.util.Procedure;

import java.util.function.Supplier;

import minicp.cp.BranchingScheme;
import minicp.engine.constraints.tp2;
import static minicp.cp.SplitDomRange.splitDomRange;

import static minicp.cp.BranchingScheme.branch;

public class SplitDomRange {
    public static Supplier<Procedure[]> splitDomRange(IntVar[] vars) {
        return () -> {
            IntVar x = null;
            int maxRange = -1;

            for (IntVar v : vars) {
                if (!v.isFixed()) {
                    int range = v.max() - v.min();
                    if (range > maxRange) {
                        maxRange = range;
                        x = v;
                    }
                }
            }

            if (x == null)
                return BranchingScheme.EMPTY; 

            final IntVar chosen = x;
            final int mid = (x.min() + x.max()) / 2;

            return branch(
                    () -> chosen.removeAbove(mid),
                    () -> chosen.removeBelow(mid + 1)
            );
        };
    }

    public static Supplier<Procedure[]> splitDomRange(IntVar x) {
        return splitDomRange(new IntVar[]{x});
    }
}
