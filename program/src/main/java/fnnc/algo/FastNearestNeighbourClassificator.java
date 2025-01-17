package fnnc.algo;

import fnnc.dist.DistanceCalculator;

import java.lang.Comparable;
import java.util.Collection;

public abstract class FastNearestNeighbourClassificator<T, D extends Comparable<D>> {
  protected DistanceCalculator<T, D> distanceCalculator;
  protected Collection<T> objectCollection;

  /**
   * Creates a fast nearest neighbour classificator.
   */
  public FastNearestNeighbourClassificator(DistanceCalculator<T, D> distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  /**
   * Prepares search for nearest neighbour.
   */
  public void preProcessing(Collection<T> objectCollection) {
    this.objectCollection = objectCollection;
  }

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public abstract T calculateNearestNeighbour(T queryObject);

  /**
   * Returns the name of the algorithm.
   */
  public abstract String getName();
}
