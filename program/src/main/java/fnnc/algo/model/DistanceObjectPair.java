package fnnc.algo.model;

public class DistanceObjectPair<D extends Comparable<D>, T>
    implements Comparable<DistanceObjectPair<D, T>> {

  private D distance;
  private T object;

  public DistanceObjectPair(D distance, T object) {
    this.distance = distance;
    this.object = object;
  }

  public int compareTo(DistanceObjectPair<D, T> distObjPair) {
    return this.getDistance().compareTo(distObjPair.getDistance());
  }

  public D getDistance() {
    return this.distance;
  }

  public void setDistance(D distance) {
    this.distance = distance;
  }

  public T getObject() {
    return this.object;
  }

  public String toString() {
    return "DistanceObjectPair[" + this.distance.toString() + ", " + this.object.toString() + "]";
  }
}
