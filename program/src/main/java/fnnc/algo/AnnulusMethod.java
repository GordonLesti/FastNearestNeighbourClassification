package fnnc.algo;

import fnnc.model.DistanceCalculator;
import fnnc.model.DistanceObjectPair;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class AnnulusMethod<T> extends FastNearestNeighbourClassificator<T, Double> {

  private T objectP;
  private LinkedList<DistanceObjectPair<Double, T>> orderedList;

  public AnnulusMethod(
      DistanceCalculator<T, Double> distanceCalculator
  ) {
    super(distanceCalculator);
  }

  /**
   * Prepares search for nearest neighboor.
   */
  public void preProcessing(Collection<T> objectCollection) {
    super.preProcessing(objectCollection);
    Random rand = new Random();
    int indexP = rand.nextInt(this.objectCollection.size());
    LinkedList<T> list = new LinkedList<T>(this.objectCollection);
    this.objectP = list.get(indexP);
    this.orderedList = new LinkedList<DistanceObjectPair<Double, T>>();
    for (T curr : this.objectCollection) {
      double distance = this.distanceCalculator.calculateDistance(curr, this.objectP);
      this.orderedList.add(new DistanceObjectPair<Double, T>(distance, curr));
    }
    Collections.sort(this.orderedList);
  }

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public T calculateNearestNeighbour(T queryObject) {
    LinkedList<DistanceObjectPair<Double, T>> listCopy =
        new LinkedList<DistanceObjectPair<Double, T>>(this.orderedList);
    Random rand = new Random();
    int randomInt = rand.nextInt(this.orderedList.size());
    ListIterator<DistanceObjectPair<Double, T>> listIterator = listCopy.listIterator(randomInt);
    boolean next = true;
    DistanceObjectPair<Double, T> distObjPairC;
    if (listIterator.hasNext()) {
      distObjPairC = listIterator.next();
    } else {
      distObjPairC = listIterator.previous();
    }
    listIterator.remove();
    T objectC = distObjPairC.getObject();
    double distanceCq = this.distanceCalculator.calculateDistance(objectC, queryObject);
    double distancePq = this.distanceCalculator.calculateDistance(this.objectP, queryObject);
    while (listIterator.hasNext() || listIterator.hasPrevious()) {
      if (next && listIterator.hasNext() || !next && !listIterator.hasPrevious()) {
        DistanceObjectPair<Double, T> distObjPairS = listIterator.next();
        listIterator.remove();
        T objectS = distObjPairS.getObject();
        double distanceSq = this.distanceCalculator.calculateDistance(objectS, queryObject);
        if (distanceSq < distanceCq) {
          distanceCq = distanceSq;
          distObjPairC = distObjPairS;
          objectC = objectS;
          continue;
        }
        if (distObjPairS.getDistance() > distancePq + distanceCq) {
          while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
          }
        }
        next = false;
      } else {
        DistanceObjectPair<Double, T> distObjPairS = listIterator.previous();
        listIterator.remove();
        T objectS = distObjPairS.getObject();
        double distanceSq = this.distanceCalculator.calculateDistance(objectS, queryObject);
        if (distanceSq < distanceCq) {
          distanceCq = distanceSq;
          distObjPairC = distObjPairS;
          objectC = objectS;
          continue;
        }
        if (distObjPairS.getDistance() < distancePq - distanceCq) {
          while (listIterator.hasPrevious()) {
            listIterator.previous();
            listIterator.remove();
          }
        }
        next = true;
      }
    }
    return objectC;
  }
}
