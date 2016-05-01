import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import fnnc.model.DistanceObjectPair;
import org.junit.Before;
import org.junit.Test;

public class DistanceObjectPairTest {

  private DistanceObjectPair<Integer, String> distObjPair;

  @Before
  public void oneTimeSetUp() {
    distObjPair = new DistanceObjectPair<Integer, String>(new Integer(5), "Object");
  }

  @Test
  public void testCompareTo() {
    DistanceObjectPair<Integer, String> otherDistObjPair =
        new DistanceObjectPair<Integer, String>(new Integer(7), "other Object");
    assertEquals(-1, this.distObjPair.compareTo(otherDistObjPair));
    assertEquals(0, this.distObjPair.compareTo(this.distObjPair));
    assertEquals(1, otherDistObjPair.compareTo(this.distObjPair));
  }

  @Test
  public void testGetDistance() {
    assertEquals(new Integer(5), this.distObjPair.getDistance());
  }

  @Test
  public void testGetObject() {
    assertEquals("Object", this.distObjPair.getObject());
  }

  @Test
  public void testSetDistance() {
    int newDistance = -8;
    this.distObjPair.setDistance(newDistance);
    assertSame(newDistance, this.distObjPair.getDistance());
  }

  @Test
  public void testToString() {
    assertEquals("DistanceObjectPair[5, Object]", this.distObjPair.toString());
  }
}
