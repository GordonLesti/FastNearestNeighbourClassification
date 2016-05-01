package fnnc.algo;

import fnnc.model.DistanceCalculator;
import fnnc.model.DistanceObjectPair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Aesa<T> extends FastNearestNeighbourClassificator<T, Double> {

  private HashMap<T, HashMap<T, Double>> distanceMatrix;

  public Aesa(DistanceCalculator<T, Double> distanceCalculator) {
    super(distanceCalculator);
  }

  /**
   * Prepares search for nearest neighboor.
   */
  public void preProcessing(Collection<T> objectCollection) {
    super.preProcessing(objectCollection);
    int size = this.objectCollection.size();
    this.distanceMatrix = new HashMap<T, HashMap<T, Double>>(size);
    for (T curr : this.objectCollection) {
      HashMap<T, Double> hashMap = new HashMap<T, Double>(size);
      boolean alreadyCalculated = true;
      for (T item : this.objectCollection) {
        if (curr == item) {
          alreadyCalculated = false;
        } else {
          double distance;
          if (alreadyCalculated) {
            distance = this.distanceMatrix.get(item).get(curr);
          } else {
            distance = this.distanceCalculator.calculateDistance(curr, item);
          }
          hashMap.put(item, distance);
        }
      }
      this.distanceMatrix.put(curr, hashMap);
    }
  }

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public T calculateNearestNeighbour(T queryObject) {
    LinkedList<DistanceObjectPair<Double, T>> unknownList =
        new LinkedList<DistanceObjectPair<Double, T>>();
    for (T curr : this.objectCollection) {
      unknownList.add(new DistanceObjectPair<Double, T>(Double.NEGATIVE_INFINITY, curr));
    }
    double smallestDistance = Double.POSITIVE_INFINITY;
    Random rand = new Random();
    DistanceObjectPair<Double, T> pair = unknownList.remove(
        rand.nextInt(unknownList.size())
    );
    T nearestNeighboor = pair.getObject();
    while (pair != null) {
      T object = pair.getObject();
      if (unknownList.size() == 0) {
        pair = null;
      }
      double distance = this.distanceCalculator.calculateDistance(object, queryObject);
      if (distance < smallestDistance) {
        smallestDistance = distance;
        nearestNeighboor = object;
      }
      double max = Double.POSITIVE_INFINITY;
      Iterator<DistanceObjectPair<Double, T>> iterator = unknownList.iterator();
      while (iterator.hasNext()) {
        DistanceObjectPair<Double, T> curr = iterator.next();
        T currObject = curr.getObject();
        double newDist = Math.max(
            curr.getDistance(),
            Math.abs(distance - this.distanceMatrix.get(object).get(currObject))
        );
        if (newDist > smallestDistance) {
          iterator.remove();
        } else {
          curr.setDistance(newDist);
          if (newDist < max) {
            max = newDist;
            pair = curr;
          }
        }
      }
      unknownList.remove(pair);
    }
    return nearestNeighboor;
  }
}
