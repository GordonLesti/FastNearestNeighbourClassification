import static org.junit.Assert.assertEquals;

import fnnc.algo.OrchardsAlgorithm;
import fnnc.model.point2ddouble.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class OrchardsAlgorithmTest {
  @Test
  public void testCalculateNearestNeighbour() {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    LinkedList<Point2D.Double> pointCollection = new LinkedList<Point2D.Double>();
    pointCollection.add(new Point2D.Double(3, 3));
    pointCollection.add(new Point2D.Double(-1, 2));
    pointCollection.add(new Point2D.Double(-4, -4));
    pointCollection.add(new Point2D.Double(0, -1));
    pointCollection.add(new Point2D.Double(4, -3));
    OrchardsAlgorithm<Point2D.Double> fnncAlgo =
        new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, pointCollection);
    assertEquals(
        new Point2D.Double(3, 3),
        fnncAlgo.calculateNearestNeighbour(new Point2D.Double(2, 1))
    );
  }
}