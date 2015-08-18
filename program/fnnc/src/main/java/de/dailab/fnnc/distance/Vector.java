package de.dailab.fnnc.distance;

/**
 * Class representing a vector in the euclidian space.
 */
public class Vector implements DistanceComputable<Vector> {

  /**
   * The values of the vector.
   */
  private double[] values;

  /**
   * Creates a new vector.
   *
   * @param entries entries
   */
  public Vector(final double[] entries) {
    this.values = entries;
  }

  /**
   * @inheritDoc
   *
   * @param vector vector
   * @return distance
   */
  public final double computeDistance(final Vector vector) {
    final int size = this.values.length;
    if (size != vector.values.length) {
      throw new RuntimeException(
      "Can not compute distance between a vector of size "
      + size + " and a vector of size " + vector.values.length
      );
    }

    double sum = 0;
    for (int i = 0; i < size; i++) {
      sum += Math.pow(this.values[i] - vector.values[i], 2);
    }
    return Math.sqrt(sum);
  }
}
