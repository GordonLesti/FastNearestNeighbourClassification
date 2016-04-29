package fnnc.model;

import java.lang.Comparable;

public abstract class DistanceCalculator<T, D extends Comparable<D>> {

  private int count = 0;

  public final D calculateDistance(T object1, T object2) {
    this.count++;
    return internalCalculateDistance(object1, object2);
  }

  /**
   * Resets the counter to 0 and returns the current count.
   */
  public final int reset() {
    int result = this.count;
    this.count = 0;
    return result;
  }

  protected abstract D internalCalculateDistance(T object1, T object2);
}
