package de.dailab.fnnc.command;

import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.command.testdata.TestData;
import java.util.Random;

/**
 * Command to create test data.
 */
public class CreateTestData implements Command {

  /**
   * Index of dim argument.
   */
  private static final int DIM_ARG_INDEX = 1;

  /**
   * Index of size argument.
   */
  private static final int SIZE_ARG_INDEX = 2;

  /**
   * Index of filename arguemnt.
   */
  private static final int FILENAME_ARG_INDEX = 3;

  /**
   * @inheritDoc
   *
   * @return name
   */
  public final String getName() {
    return "create-test-data";
  }

  /**
   * @inheritDoc
   *
   * @param args arguments
   */
  public final void run(final String[] args) {
    if (args.length < this.SIZE_ARG_INDEX + 1) {
      System.out.println("Usage: create-test-data DIM SIZE [FILENAME]");
      return;
    }
    int dim = Integer.parseInt(args[this.DIM_ARG_INDEX]);
    if (dim <= 0) {
      System.out.println("DIM has to be greater than 0.");
      return;
    }
    int size = Integer.parseInt(args[this.SIZE_ARG_INDEX]);
    if (size <= 0) {
      System.out.println("Size has to be greater than 0.");
    }
    String filename = dim + "-" + size + "-" + System.currentTimeMillis();
    if (args.length > this.FILENAME_ARG_INDEX) {
      filename = args[this.FILENAME_ARG_INDEX];
    }
    System.out.println("Starting create-test-data with DIM=" + dim + " SIZE="
    + size + " FILENAME=" + filename);
    Random rand = new Random();
    Vector[] vectorSet = new Vector[size];
    for (int i = 0; i < size; i++) {
      double[] values = new double[dim];
      for (int j = 0; j < dim; j++) {
        values[j] = rand.nextInt();
      }
      vectorSet[i] = new Vector(values);
    }
    TestData.write(vectorSet, filename);
  }
}
