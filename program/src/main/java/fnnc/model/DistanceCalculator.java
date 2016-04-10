package fnnc.model;

import java.lang.Comparable;

public interface DistanceCalculator<T, D extends Comparable<D>> {
  public D calculateDistance(T object1, T object2);
}
