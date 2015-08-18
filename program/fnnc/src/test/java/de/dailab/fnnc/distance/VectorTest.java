package de.dailab.fncc.distance;

import junit.framework.TestCase;
import de.dailab.fnnc.distance.Vector;

public class VectorTest extends TestCase {

  public void testComputeDistance() {
    double[] entries1 = {1.0, 2.0};
    Vector vector1 = new Vector(entries1);
    double[] entries2 = {-0.5, -3.0};
    Vector vector2 = new Vector(entries2);

    assertEquals(vector1.computeDistance(vector2), Math.sqrt(27.25));
  }

  public void testComputeDistanceRuntimeException() {
    double[] entries1 = {1.0};
    Vector vector1 = new Vector(entries1);
    double[] entries2 = {-0.5, -3.0};
    Vector vector2 = new Vector(entries2);
    try {
      vector1.computeDistance(vector2);
      fail(
        "Should throw an exception that the vectors have different dimensions"
      );
    } catch (RuntimeException e) {
      assertEquals(
        e.getMessage(),
        "Can not compute distance between a vector of size 1 and a vector of si"
          + "ze 2"
      );
    }
  }
}
