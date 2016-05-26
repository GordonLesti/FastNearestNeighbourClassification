package fnnc.algo;

import fnnc.algo.model.DistanceObjectPair;
import fnnc.dist.DistanceCalculator;

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
  private boolean useMarkBits;

  /**
   * Creates an OrchardsAlgorithm object.
   */
  public OrchardsAlgorithm(
      DistanceCalculator<T, Double> distanceCalculator,
      boolean useMarkBits
  ) {
    super(distanceCalculator);
    this.useMarkBits = useMarkBits;
  }

  /**
   * Prepares search for nearest neighbour.
   */
  public void preProcessing(Collection<T> objectCollection) {
    super.preProcessing(objectCollection);
    this.orderedLists =
        new HashMap<Integer, LinkedList<DistanceObjectPair<Double, Integer>>>(
          objectCollection.size()
        );
    this.indexedObjects =
        new HashMap<Integer, T>(objectCollection.size());
    int counter = 0;
    for (T curr : this.objectCollection) {
      LinkedList<DistanceObjectPair<Double, Integer>> itemList =
          new LinkedList<DistanceObjectPair<Double, Integer>>();
      this.orderedLists.put(counter, itemList);
      this.indexedObjects.put(counter, curr);
      counter++;
    }
    counter = 0;
    for (T curr : this.objectCollection) {
      LinkedList<DistanceObjectPair<Double, Integer>> itemList = this.orderedLists.get(counter);
      int innerCounter = 0;
      for (T item : this.objectCollection) {
        if (curr == item) {
          break;
        }
        double distance = this.distanceCalculator.calculateDistance(curr, item);
        itemList.add(new DistanceObjectPair<Double, Integer>(distance, innerCounter));
        this.orderedLists.get(innerCounter).add(
            new DistanceObjectPair<Double, Integer>(distance, counter)
        );
        innerCounter++;
      }
      counter++;
    }
    for (int i = 0; i < counter; i++) {
      Collections.sort(this.orderedLists.get(i));
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
    boolean[] markBits = new boolean[this.objectCollection.size()];
    LinkedList<DistanceObjectPair<Double, Integer>> currentList =
        this.orderedLists.get(currentIndex);
    Iterator<DistanceObjectPair<Double, Integer>> iterator = currentList.iterator();
    while (iterator.hasNext()) {
      DistanceObjectPair<Double, Integer> tempDistObjPair = iterator.next();
      int tempIndex = tempDistObjPair.getObject();
      if (!this.useMarkBits || !markBits[tempIndex]) {
        T tempObject = this.indexedObjects.get(tempIndex);
        if (tempDistObjPair.getDistance() > 2 * currentDistanceToQuery) {
          break;
        }
        double tempDistanceToQuery = this.distanceCalculator
            .calculateDistance(tempObject, queryObject);
        markBits[tempIndex] = true;
        if (tempDistanceToQuery < currentDistanceToQuery) {
          currentDistanceToQuery = tempDistanceToQuery;
          currentObject = tempObject;
          currentIndex = tempIndex;
          currentList = this.orderedLists.get(currentIndex);
          iterator = currentList.iterator();
        }
      }
    }
    return currentObject;
  }

  public String getName() {
    String name = "Orchard";
    if (this.useMarkBits) {
      name += " MarkBits";
    }
    return name;
  }
}
