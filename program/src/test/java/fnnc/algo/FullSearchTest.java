import static org.junit.Assert.assertEquals;

import fnnc.algo.FullSearch;
import fnnc.model.point2ddouble.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class FullSearchTest {
  @Test
  public void testCalculateNearestNeighbour() {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    LinkedList<Point2D.Double> pointCollection = new LinkedList<Point2D.Double>();
    pointCollection.add(new Point2D.Double(3, 3));
    pointCollection.add(new Point2D.Double(-1, 2));
    pointCollection.add(new Point2D.Double(-4, -4));
    pointCollection.add(new Point2D.Double(0, -1));
    pointCollection.add(new Point2D.Double(4, -3));
    FullSearch<Point2D.Double, Double> fnncAlgo =
        new FullSearch<Point2D.Double, Double>(distanceCalculator, pointCollection);
    assertEquals(
        new Point2D.Double(3, 3),
        fnncAlgo.calculateNearestNeighbour(new Point2D.Double(2, 1))
    );
  }
}