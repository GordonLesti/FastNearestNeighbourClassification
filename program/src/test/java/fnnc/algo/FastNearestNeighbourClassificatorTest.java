import static org.junit.Assert.assertEquals;

import fnnc.algo.Aesa;
import fnnc.algo.AnnulusMethod;
import fnnc.algo.FastNearestNeighbourClassificator;
import fnnc.algo.FullSearch;
import fnnc.algo.OrchardsAlgorithm;
import fnnc.app.DistanceCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class FastNearestNeighbourClassificatorTest {
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
    Point2D.Double nearestNeighbour = new Point2D.Double(3.76, 0.81);
    Point2D.Double query = new Point2D.Double(4.42, 0.78);
    LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> algos =
        this.getAlgos(distanceCalculator);
    for (FastNearestNeighbourClassificator<Point2D.Double, Double> algo : algos) {
      algo.preProcessing(pointCollection);
      assertEquals(nearestNeighbour, algo.calculateNearestNeighbour(query));
    }
  }

  private LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> getAlgos(
      DistanceCalculator distanceCalculator
  ) {
    LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> algos =
        new LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>>();
    algos.add(new Aesa<Point2D.Double>(distanceCalculator));
    algos.add(new AnnulusMethod<Point2D.Double>(distanceCalculator));
    algos.add(new FullSearch<Point2D.Double, Double>(distanceCalculator));
    algos.add(new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, true));
    algos.add(new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, false));
    return algos;
  }
}
