package de.dailab.fnnc.distance;

/**
 * A interface for objects to compute the distance between these objects.
 *
 * @param <T> the type of objects that this object may be compared to
 */
public interface DistanceComputable<T> {

  /**
   * Computes the distance between this object and the given one.
   *
   * @param object the object to compute the distance from.
   * @return distance
   */
  double computeDistance(T object);
}
