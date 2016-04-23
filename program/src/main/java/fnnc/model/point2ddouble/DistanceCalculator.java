package fnnc.model.point2ddouble;

import java.awt.geom.Point2D;

public class DistanceCalculator extends fnnc.model.DistanceCalculator<Point2D.Double, Double> {

  /**
   * Calculates the distance between the two objects.
   */
  protected Double internalCalculateDistance(Point2D.Double point1, Point2D.Double point2) {
    double xdiff = point1.x - point2.x;
    double ydiff = point1.y - point2.y;
    return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
  }
}
