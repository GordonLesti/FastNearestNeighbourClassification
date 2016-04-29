package fnnc.algo;

import fnnc.model.DistanceCalculator;

import java.lang.Comparable;
import java.util.Collection;

public class FullSearch<T, D extends Comparable<D>>
    extends FastNearestNeighbourClassificator<T, D> {

  public FullSearch(DistanceCalculator<T, D> distanceCalculator) {
    super(distanceCalculator);
  }

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public T calculateNearestNeighbour(T queryObject) {
    D smallestDist = null;
    T nearestNeighbour = null;
    for (T item : this.objectCollection) {
      D itemDist = this.distanceCalculator.calculateDistance(queryObject, item);
      if (smallestDist == null || smallestDist.compareTo(itemDist) == 1) {
        smallestDist = itemDist;
        nearestNeighbour = item;
      }
    }
    return nearestNeighbour;
  }
}
