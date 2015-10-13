package de.dailab.fnnc;

import java.util.Random;
import de.dailab.fnnc.framework.TestDataCreator;
import de.dailab.fnnc.distance.Vector;

/**
 * Application to nur the benchmark of Fast Nearest Neighbor Search algorithms.
 */
public final class App {

  /**
   * Size of the vector set.
   */
  private static final int SIZE = 1000;

  /**
   * Dimension of the vectors.
   */
  private static final int DIM = 10;

  /**
   * Maximal value for a vector entry.
   */
  private static final int MAX_VALUE = 1000000;

  /**
   * Hided default constructor for App.
   */
  private App() {
  }

  /**
   * Main function to start the App.
   *
   * @param args arguments
   */
  public static void main(final String[] args) {
    Random rand = new Random();
    Vector[] vectors = new Vector[this.SIZE];
    for (int i = 0; i < this.SIZE; i++) {
      double[] entries = new double[this.DIM];
      for (int j = 0; j < this.DIM; j++) {
        double value = rand.nextInt(this.MAX_VALUE);
        if (rand.nextBoolean()) {
          value *= -1;
        }
        entries[j] = value;
      }
      vectors[i] = new Vector(entries);
    }
    TestDataCreator.write(vectors, "1000_10");
    vectors = TestDataCreator.read("1000_10");
    for (int i = 0; i < vectors.length; i++) {
      System.out.println(vectors[i].toString());
    }
  }
}
