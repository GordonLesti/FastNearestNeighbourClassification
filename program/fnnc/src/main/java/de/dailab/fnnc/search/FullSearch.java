package de.dailab.fnnc.search;

import de.dailab.fnnc.distance.Vector;

/**
 * Fullsearch algorithm.
 */
public class FullSearch {

  /**
   * Set of vectors.
   */
  private Vector[] vectors;

  /**
   * Creates a FullSearch.
   *
   * @param vectorSet given set of vectors
   */
  public FullSearch(final Vector[] vectorSet) {
    this.vectors = vectorSet;
  }

  /**
   * Returns the nearest neighbor of the given object.
   *
   * @param vector given query vector
   * @return nearest neighbor
   */
  public final Vector getNearestNeighbor(final Vector vector) {
    double smallestDistance = Double.POSITIVE_INFINITY;
    int smallestIndex = 0;
    for (int i = 0; i < this.vectors.length; i++) {
      final double currentDistance = this.vectors[i].computeDistance(vector);
      if (currentDistance < smallestDistance) {
        smallestIndex = i;
        smallestDistance = currentDistance;
      }
    }
    return this.vectors[smallestIndex];
  }
}
