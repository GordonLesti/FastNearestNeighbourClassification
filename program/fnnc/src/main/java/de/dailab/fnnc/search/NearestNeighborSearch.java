package de.dailab.fnnc.search;

import de.dailab.fnnc.distance.Vector;

/**
 * Interface for a nearest neighbor search algorithm.
 */
public interface NearestNeighborSearch {

  /**
   * Sets an array of vectors to the algorithm.
   *
   * @param vectorSet vetorSet
   */
  void setVectors(Vector[] vectorSet);

  /**
   * Returns the nearest neighbor result.
   *
   * @param vector vector
   * @return neighbor
   */
  Vector getNearestNeighbor(Vector vector);
}
