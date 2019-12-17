package com.baeldung.algorithms0;

import com.baeldung.algorithms0.ga.ant_colony.AntColonyOptimization;
import org.junit.Assert;
import org.junit.Test;


public class AntColonyOptimizationLongRunningUnitTest {

    @Test
    public void testGenerateRandomMatrix() {
        AntColonyOptimization antTSP = new AntColonyOptimization(5);
        Assert.assertNotNull(antTSP.generateRandomMatrix(5));
    }

    @Test
    public void testStartAntOptimization() {
        AntColonyOptimization antTSP = new AntColonyOptimization(5);
        Assert.assertNotNull(antTSP.solve());
    }

}
