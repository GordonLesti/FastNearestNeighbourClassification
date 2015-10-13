package de.dailab.fnnc.search.orchard;

public class VectorDistance implements Comparable<VectorDistance> {
  private int vectorIndex;

  private double distance;

  public VectorDistance(int vectorIndex, double dist) {
    this.vectorIndex = vectorIndex;
    this.distance = dist;
  }

  public double getDistance() {
    return this.distance;
  }

  public int getVectorIndex() {
    return this.vectorIndex;
  }

  public int compareTo(VectorDistance vecDist) {
    if (this.distance < vecDist.distance) {
      return -1;
    }
    if (this.distance == vecDist.distance) {
      return 0;
    }
    return 1;
  }

  public String toString() {
    return "Intex: "+this.vectorIndex+" Distance: "+this.distance;
  }
}
