package de.dailab.fnnc.search;

import java.util.Arrays;
import java.util.Random;
import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.search.orchard.VectorDistance;

public class AnnulusMethod implements NearestNeighborSearch{

  /**
   * Set of vectors.
   */
  private Vector[] vectors;

  private int pStarIndex;

  private VectorDistance[] list;

  private boolean[] visited;

  private int highestVisitedSIndex;

  private int lowestVisitedSindex;

  private int upperBound;

  private int lowerBound;

  public void setVectors(final Vector[] vectorSet) {
    this.vectors = vectorSet;
    Random rand = new Random();
    int length = this.vectors.length;
    this.pStarIndex = rand.nextInt(length);
    this.list = new VectorDistance[length];
    for (int i = 0; i < length; i++) {
      double distance = this.vectors[this.pStarIndex].computeDistance(this.vectors[i]);
      this.list[i] = new VectorDistance(i, distance);
    }
    Arrays.sort(this.list);
  }

  /**
   * Returns the nearest neighbor of the given object.
   *
   * @param vector given query vector
   * @return nearest neighbor
   */
  public final Vector getNearestNeighbor(final Vector vector) {
    this.visited = new boolean[this.list.length];
    Random rand = new Random();
    int cIndexInPList = rand.nextInt(this.list.length);
    this.highestVisitedSIndex = cIndexInPList;
    this.lowestVisitedSindex = cIndexInPList;
    this.upperBound = Integer.MIN_VALUE;
    this.lowerBound = Integer.MAX_VALUE;
    return this.vectors[cIndexInPList];
  }
}
