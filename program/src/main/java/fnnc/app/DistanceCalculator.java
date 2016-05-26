package fnnc.app;

import java.awt.geom.Point2D;

public class DistanceCalculator extends fnnc.dist.DistanceCalculator<Point2D.Double, Double> {

  /**
   * Calculates the distance between the two objects.
   */
  protected Double internalCalculateDistance(Point2D.Double point1, Point2D.Double point2) {
    return point1.distance(point2);
  }
}
