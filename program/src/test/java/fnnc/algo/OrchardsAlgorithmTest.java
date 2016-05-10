import static org.junit.Assert.assertEquals;

import fnnc.algo.OrchardsAlgorithm;
import fnnc.model.point2ddouble.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class OrchardsAlgorithmTest {
  @Test
  public void testCalculateNearestNeighbourMarkBits() {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    LinkedList<Point2D.Double> pointCollection = new LinkedList<Point2D.Double>();
    pointCollection.add(new Point2D.Double(5.40, 9.80));
    pointCollection.add(new Point2D.Double(1.64, 3.30));
    pointCollection.add(new Point2D.Double(9.62, 3.19));
    pointCollection.add(new Point2D.Double(0.33, 6.85));
    pointCollection.add(new Point2D.Double(4.73, 6.12));
    pointCollection.add(new Point2D.Double(5.26, 6.32));
    pointCollection.add(new Point2D.Double(1.83, 6.58));
    pointCollection.add(new Point2D.Double(5.98, 0.69));
    pointCollection.add(new Point2D.Double(9.59, 8.10));
    pointCollection.add(new Point2D.Double(3.76, 0.81));
    OrchardsAlgorithm<Point2D.Double> fnncAlgo =
        new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, true);
    fnncAlgo.preProcessing(pointCollection);
    assertEquals(
        new Point2D.Double(3.76, 0.81),
        fnncAlgo.calculateNearestNeighbour(new Point2D.Double(4.42, 0.78))
    );
  }

  @Test
  public void testCalculateNearestNeighbour() {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    LinkedList<Point2D.Double> pointCollection = new LinkedList<Point2D.Double>();
    pointCollection.add(new Point2D.Double(5.40, 9.80));
    pointCollection.add(new Point2D.Double(1.64, 3.30));
    pointCollection.add(new Point2D.Double(9.62, 3.19));
    pointCollection.add(new Point2D.Double(0.33, 6.85));
    pointCollection.add(new Point2D.Double(4.73, 6.12));
    pointCollection.add(new Point2D.Double(5.26, 6.32));
    pointCollection.add(new Point2D.Double(1.83, 6.58));
    pointCollection.add(new Point2D.Double(5.98, 0.69));
    pointCollection.add(new Point2D.Double(9.59, 8.10));
    pointCollection.add(new Point2D.Double(3.76, 0.81));
    OrchardsAlgorithm<Point2D.Double> fnncAlgo =
        new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, false);
    fnncAlgo.preProcessing(pointCollection);
    assertEquals(
        new Point2D.Double(3.76, 0.81),
        fnncAlgo.calculateNearestNeighbour(new Point2D.Double(4.42, 0.78))
    );
  }
}
