package de.dailab.fncc.search;

import junit.framework.TestCase;
import de.dailab.fnnc.search.FullSearch;
import de.dailab.fnnc.distance.Vector;

public class FullSearchTest extends TestCase {

  public void testGetNearestNeighbor() {
    double[] entries1 = {1.0, 2.0};
    Vector vector1 = new Vector(entries1);
    double[] entries2 = {-0.5, -3.0};
    Vector vector2 = new Vector(entries2);
    double[] entries3 = {-1.0, -3.0};
    Vector vector3 = new Vector(entries3);

    Vector[] vectorSet = {vector1, vector2};
    FullSearch fsearch = new FullSearch(vectorSet);
    assertEquals(fsearch.getNearestNeighbor(vector3), vector2);
  }
}
