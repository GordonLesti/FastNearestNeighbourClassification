package fnnc.algo;

import fnnc.model.DistanceCalculator;
import fnnc.model.DistanceObjectPair;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class OrchardsAlgorithm<T> extends FastNearestNeighbourClassificator<T, Double> {

  private HashMap<Integer, LinkedList<DistanceObjectPair<Double, Integer>>> orderedLists;
  private HashMap<Integer, T> indexedObjects;

  /**
   * Creates an OrchardsAlgorithm object.
   */
  public OrchardsAlgorithm(
      DistanceCalculator<T, Double> distanceCalculator,
      Collection<T> objectCollection
  ) {
    super(distanceCalculator, objectCollection);
    this.orderedLists =
        new HashMap<Integer, LinkedList<DistanceObjectPair<Double, Integer>>>(
          objectCollection.size()
        );
    this.indexedObjects =
        new HashMap<Integer, T>(objectCollection.size());
    this.preProcessing();
  }

  protected void preProcessing() {
    int counter = 0;
    for (T curr : this.objectCollection) {
      LinkedList<DistanceObjectPair<Double, Integer>> itemList =
          new LinkedList<DistanceObjectPair<Double, Integer>>();
      int innerCounter = 0;
      for (T item : this.objectCollection) {
        if (curr != item) {
          double distance = this.distanceCalculator.calculateDistance(curr, item);
          itemList.add(new DistanceObjectPair<Double, Integer>(distance, innerCounter));
        }
        innerCounter++;
      }
      Collections.sort(itemList);
      this.orderedLists.put(counter, itemList);
      this.indexedObjects.put(counter, curr);
      counter++;
    }
  }

  /**
   * Calculates the nearest neighbour of the given query object.
   */
  public T calculateNearestNeighbour(T queryObject) {
    Random random = new Random();
    int currentIndex = random.nextInt(this.objectCollection.size());
    T currentObject = this.indexedObjects.get(currentIndex);
    double currentDistanceToQuery = this.distanceCalculator
        .calculateDistance(currentObject, queryObject);
    LinkedList<DistanceObjectPair<Double, Integer>> currentList =
        this.orderedLists.get(currentIndex);
    Iterator<DistanceObjectPair<Double, Integer>> iterator = currentList.iterator();
    while (iterator.hasNext()) {
      DistanceObjectPair<Double, Integer> tempDistObjPair = iterator.next();
      T tempObject = this.indexedObjects.get(tempDistObjPair.getObject());
      if (tempDistObjPair.getDistance() > 2 * currentDistanceToQuery) {
        break;
      }
      double tempDistanceToQuery = this.distanceCalculator
          .calculateDistance(tempObject, queryObject);
      if (tempDistanceToQuery < currentDistanceToQuery) {
        currentDistanceToQuery = tempDistanceToQuery;
        currentIndex = tempDistObjPair.getObject();
        currentObject = tempObject;
        currentList = this.orderedLists.get(currentIndex);
        iterator = currentList.iterator();
      }
    }
    return currentObject;
  }
}
