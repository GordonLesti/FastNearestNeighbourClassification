package de.dailab.fnnc.search;

import java.util.Arrays;
import java.util.Random;
import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.search.orchard.VectorDistance;

/**
 * Orchard's Algorithmus.
 */
public class OrchardAlgorithmus implements NearestNeighborSearch {

  /**
   * Set of vectors.
   */
  private Vector[] vectors;

  /**
   * Ordered Lists for every vector to each other vector.
   */
  private VectorDistance[][] lists;

  /**
   * @inheritDoc
   *
   * @return name
   */
  public final String getName() {
    return "orchard";
  }

  /**
   * @inheritDoc
   *
   * @param vectorSet vectorSet
   */
  public final void setVectors(final Vector[] vectorSet) {
    this.vectors = vectorSet;
    int length = this.vectors.length;
    this.lists = new VectorDistance[length][length - 1];
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        Vector vectorA = this.vectors[i];
        Vector vectorB = this.vectors[j];
        double distance = vectorA.computeDistance(vectorB);
        this.lists[i][j - 1] = new VectorDistance(j, distance);
        this.lists[j][i] = new VectorDistance(i, distance);
      }
      Arrays.sort(this.lists[i]);
    }
  }

  /**
   * Returns the nearest neighbor of the given object.
   *
   * @param vector given query vector
   * @return nearest neighbor
   */
  public final Vector getNearestNeighbor(final Vector vector) {
    Random rand = new Random();
    int cIndex = rand.nextInt(this.vectors.length);
    double distanceCQ = this.vectors[cIndex].computeDistance(vector);
    int sIndexInCList = 0;
    boolean stop = false;
    int distanceCount = 0;
    while (!stop) {
      int sIndex = this.lists[cIndex][sIndexInCList].getVectorIndex();
      distanceCount++;
      double distanceSQ = this.vectors[sIndex].computeDistance(vector);
      if (distanceSQ < distanceCQ) {
        cIndex = sIndex;
        sIndexInCList = 0;
        distanceCQ = distanceSQ;
      } else if (sIndexInCList == this.vectors.length - 2
      || this.lists[cIndex][sIndexInCList].getDistance() > 2 * distanceCQ) {
        stop = true;
      } else {
        sIndexInCList++;
      }
    }
    System.out.println("ORCHARD DISTANCE_COUNT:" + distanceCount);
    return this.vectors[cIndex];
  }
}
