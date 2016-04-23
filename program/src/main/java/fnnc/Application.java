package fnnc;

import fnnc.algo.AnnulusMethod;
import fnnc.algo.FullSearch;
import fnnc.algo.OrchardsAlgorithm;
import fnnc.model.point2ddouble.DistanceCalculator;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;

public class Application {
  public static void main(String[] args) {
    LinkedList<Point2D.Double> list = createTestData(Integer.parseInt(args[0]));
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    AnnulusMethod<Point2D.Double> annulusMethod = new AnnulusMethod<Point2D.Double>(distanceCalculator, list);
    System.out.println(distanceCalculator.reset());
    FullSearch<Point2D.Double, Double> fullSearch = new FullSearch<Point2D.Double, Double>(distanceCalculator, list);
    System.out.println(distanceCalculator.reset());
    OrchardsAlgorithm<Point2D.Double> orchardsAlgorithm =
        new OrchardsAlgorithm<Point2D.Double>(distanceCalculator, list);
    System.out.println(distanceCalculator.reset());

    Random rand = new Random();
    Point2D.Double queryPoint = new Point2D.Double(rand.nextDouble(), rand.nextDouble());
    Point2D.Double annulusMethodResult = annulusMethod.calculateNearestNeighbour(queryPoint);
    System.out.println(distanceCalculator.reset());
    Point2D.Double fullSearchResult = fullSearch.calculateNearestNeighbour(queryPoint);
    System.out.println(distanceCalculator.reset());
    Point2D.Double orchardsAlgorithmResult = orchardsAlgorithm.calculateNearestNeighbour(queryPoint);
    System.out.println(distanceCalculator.reset());

    if (fullSearchResult != annulusMethodResult || annulusMethodResult != orchardsAlgorithmResult) {
        System.out.println("ERROR");
    } else {
        System.out.println("SUCCESS");
    }
  }

  private static LinkedList<Point2D.Double> createTestData(int number)
  {
      LinkedList<Point2D.Double> list = new LinkedList<Point2D.Double>();
      Random rand = new Random();
      for (int i = 0; i < number; i++) {
          list.add(new Point2D.Double(rand.nextDouble(), rand.nextDouble()));
      }
      return list;
  }
}
