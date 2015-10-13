package de.dailab.fnnc;

import java.util.Random;
import de.dailab.fnnc.framework.TestDataCreator;
import de.dailab.fnnc.distance.Vector;

/**
 * Hello world!
 *
 */
public final class App {

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
    int size = 1000;
    int dim = 10;
    Random rand = new Random();
    Vector[] vectors = new Vector[size];
    for (int i = 0; i < size; i++) {
      double[] entries = new double[dim];
      for (int j = 0; j < dim; j++) {
        double value = rand.nextInt(1000000);
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
