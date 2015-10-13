package de.dailab.fncc.search;

import junit.framework.TestCase;
import de.dailab.fnnc.search.AnnulusMethod;
import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.framework.TestDataCreator;

public class AnnulusMethodTest extends TestCase {

  public void testGetNearestNeighbor() {
    double[] entries1 = {1.0, 2.0};
    Vector vector1 = new Vector(entries1);
    double[] entries2 = {-0.5, -3.0};
    Vector vector2 = new Vector(entries2);
    double[] entries3 = {-1.0, -3.0};
    Vector vector3 = new Vector(entries3);
    double[] entries4 = {-7, 8};
    Vector vector4 = new Vector(entries4);

    Vector[] vectorSet = {vector1, vector2, vector4};
    AnnulusMethod annulus = new AnnulusMethod();
    annulus.setVectors(vectorSet);
    assertEquals(annulus.getNearestNeighbor(vector3), vector2);
  }

  public void testGetNearestNeighborS1000d50() {
    Vector[] vectors = TestDataCreator.read("target/test-classes/1000_10");
    double[] queryEntries = {
      937504.0,
      538101.0,
      469577.0,
      87396.0,
      570752.0,
      539950.0,
      650209.0,
      190277.0,
      -369600.0,
      -461315.0
    };
    Vector queryVector = new Vector(queryEntries);
    AnnulusMethod annulus = new AnnulusMethod();
    annulus.setVectors(vectors);
    double[] nnEntries = {
      870784.0,
      53276.0,
      -55542.0,
      507680.0,
      469754.0,
      354278.0,
      639826.0,
      271537.0,
      -135787.0,
      -362347.0
    };
    Vector nearestNeighbor = new Vector(nnEntries);
    assertEquals(annulus.getNearestNeighbor(queryVector), nearestNeighbor);
  }
}
