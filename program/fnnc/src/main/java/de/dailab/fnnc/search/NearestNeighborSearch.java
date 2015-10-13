package de.dailab.fnnc.search;

import de.dailab.fnnc.distance.Vector;

public interface NearestNeighborSearch {
  public void setVectors(Vector[] vectorSet);

  public Vector getNearestNeighbor(Vector vector);
}
