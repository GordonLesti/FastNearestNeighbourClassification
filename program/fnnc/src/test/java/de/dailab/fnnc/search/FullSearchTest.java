package de.dailab.fncc.search;

import junit.framework.TestCase;
import de.dailab.fnnc.search.FullSearch;
import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.command.testdata.TestData;

public class FullSearchTest extends TestCase {

  public void testGetNearestNeighbor() {
    Vector[] testVectorSet = TestData.read("target/test-classes/3-10-test");
    Vector[] queryVectorSet = TestData.read("target/test-classes/3-1-query");
    FullSearch fsearch = new FullSearch();
    fsearch.setVectors(testVectorSet);
    double[] expectedResultEntries = {1.216913797E9, -3.14664324E8, 7.88973534E8};
    Vector expectedResultVector = new Vector(expectedResultEntries);
    assertEquals(expectedResultVector, fsearch.getNearestNeighbor(queryVectorSet[0]));
  }
}
