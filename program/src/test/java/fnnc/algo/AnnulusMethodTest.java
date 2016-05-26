import static org.junit.Assert.assertEquals;

import fnnc.algo.AnnulusMethod;
import fnnc.app.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class AnnulusMethodTest {
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
    AnnulusMethod<Point2D.Double> fnncAlgo = new AnnulusMethod<Point2D.Double>(distanceCalculator);
    fnncAlgo.preProcessing(pointCollection);
    assertEquals(
        new Point2D.Double(3.76, 0.81),
        fnncAlgo.calculateNearestNeighbour(new Point2D.Double(4.42, 0.78))
    );
  }
}
