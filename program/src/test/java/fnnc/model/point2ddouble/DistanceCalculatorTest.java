import static org.junit.Assert.assertEquals;

import fnnc.model.point2ddouble.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;

public class DistanceCalculatorTest {
  @Test
  public void testCalculateDistance() {
    Point2D.Double point1 = new Point2D.Double(3, -2);
    Point2D.Double point2 = new Point2D.Double(-4, 1);
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    assertEquals(7.615773105863909, distanceCalculator.calculateDistance(point1, point2), 0);
  }
}
