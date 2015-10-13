package de.dailab.fnnc.search;

import de.dailab.fnnc.distance.Vector;

/**
 * Fullsearch algorithm.
 */
public class FullSearch implements NearestNeighborSearch{

  /**
   * Set of vectors.
   */
  private Vector[] vectors;

  public void setVectors(final Vector[] vectorSet) {
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
    int distanceCount = 0;
    for (int i = 0; i < this.vectors.length; i++) {
      final double currentDistance = this.vectors[i].computeDistance(vector);
      distanceCount++;
      if (currentDistance < smallestDistance) {
        smallestIndex = i;
        smallestDistance = currentDistance;
      }
    }
    System.out.println("FULLSEARCH DISTANCE_COUNT:"+distanceCount);
    return this.vectors[smallestIndex];
  }
}
