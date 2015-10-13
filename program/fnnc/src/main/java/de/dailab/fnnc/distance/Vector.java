package de.dailab.fnnc.distance;

import java.io.Serializable;

/**
 * Class representing a vector in the euclidian space.
 */
public class Vector implements Serializable {

  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 42L;

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

  /**
   * Returns a string representing the vector.
   *
   * @return string
   */
  public final String toString() {
    String output = "<";
    int lastIndex = this.values.length - 1;
    for (int i = 0; i < lastIndex; i++) {
      output += this.values[i] + ", ";
    }
    output += this.values[lastIndex] + ">";
    return output;
  }

  /**
   * Checks if an object is equals to this vector.
   *
   * @param object object
   * @return equals
   */
  public final boolean equals(final Object object) {
    if (object instanceof Vector) {
      Vector vector = (Vector) object;
      for (int i = 0; i < this.values.length; i++) {
        if (this.values[i] != vector.values[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Creates a hashCode of the vector.
   *
   * @return hash
   */
  public final int hashCode() {
    return super.hashCode();
  }
}
