package de.dailab.fncc.search;

import junit.framework.TestCase;
import de.dailab.fnnc.search.AnnulusMethod;
import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.command.testdata.TestData;

public class AnnulusMethodTest extends TestCase {

    public void testGetNearestNeighbor() {
      Vector[] testVectorSet = TestData.read("target/test-classes/3-10-test");
      Vector[] queryVectorSet = TestData.read("target/test-classes/3-1-query");
      AnnulusMethod annulus = new AnnulusMethod();
      annulus.setVectors(testVectorSet);
      double[] expectedResultEntries = {1.216913797E9, -3.14664324E8, 7.88973534E8};
      Vector expectedResultVector = new Vector(expectedResultEntries);
      assertEquals(expectedResultVector, annulus.getNearestNeighbor(queryVectorSet[0]));
    }
}
