package fnnc.algo;

import fnnc.model.DistanceCalculator;

import java.lang.Comparable;
import java.util.Collection;

public abstract class FastNearestNeighbourClassificator<T, D extends Comparable<D>> {
  protected DistanceCalculator<T, D> distanceCalculator;
  protected Collection<T> objectCollection;

  /**
   * Creates a fast nearest neighbour classificator.
   */
  public FastNearestNeighbourClassificator(
      DistanceCalculator<T, D> distanceCalculator,
      Collection<T> objectCollection
  ) {
    this.distanceCalculator = distanceCalculator;
    this.objectCollection = objectCollection;
  }

  protected abstract void preProcessing();

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public abstract T calculateNearestNeighbour(T queryObject);
}
