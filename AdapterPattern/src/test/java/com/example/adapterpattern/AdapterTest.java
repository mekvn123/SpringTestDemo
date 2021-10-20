package com.example.adapterpattern;

import com.example.adapterpattern.service.RoundHole;
import com.example.adapterpattern.service.RoundPeg;
import com.example.adapterpattern.service.SquarePeg;
import com.example.adapterpattern.service.SquarePegAdapter;
import org.junit.jupiter.api.Test;

public class AdapterTest {
    @Test
    void should_success() {
        // Round fits round, no surprise.
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }


        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // Won't compile.

        // Adapter solves the problem.

        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);

        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }

}
