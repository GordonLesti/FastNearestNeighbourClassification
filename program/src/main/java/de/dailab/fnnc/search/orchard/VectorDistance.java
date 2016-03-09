package de.dailab.fnnc.search.orchard;

/**
 * A class that represents a pair of a distance and a vector index.
 */
public class VectorDistance implements Comparable<VectorDistance> {

  /**
   * The index of the vector.
   */
  private int vectorIndex;

  /**
   * The distance of this vector to an other vector.
   */
  private double distance;

  /**
   * The constructor of VectorDistance.
   *
   * @param index vectorIndex
   * @param dist distance
   */
  public VectorDistance(final int index, final double dist) {
    this.vectorIndex = index;
    this.distance = dist;
  }

  /**
   * Returns the distance.
   *
   * @return distance
   */
  public final double getDistance() {
    return this.distance;
  }

  /**
   * Returns the index of the vector.
   *
   * @return vectorIndex
   */
  public final int getVectorIndex() {
    return this.vectorIndex;
  }

  /**
   * Compares two VectorDistance objects.
   *
   * @param vecDist other VectorDistance object
   * @return comp
   */
  public final int compareTo(final VectorDistance vecDist) {
    if (this.distance < vecDist.distance) {
      return -1;
    }
    if (this.distance == vecDist.distance) {
      return 0;
    }
    return 1;
  }

  /**
   * Returns a strign representing the VectorDistance.
   *
   * @return string
   */
  public final String toString() {
    return "Intex: " + this.vectorIndex + " Distance: " + this.distance;
  }
}
