package de.dailab.fnnc.command;

import de.dailab.fnnc.distance.Vector;
import de.dailab.fnnc.command.testdata.TestData;
import de.dailab.fnnc.search.NearestNeighborSearch;
import de.dailab.fnnc.search.FullSearch;
import de.dailab.fnnc.search.OrchardAlgorithmus;
import java.util.LinkedList;

/**
 * Command to benchmark a algorithm.
 */
public class Benchmark implements Command {

  /**
   * Index of algorithm argument.
   */
  private static final int ALGO_ARG_INDEX = 1;

  /**
   * Index of test-data argument.
   */
  private static final int TEST_DATA_ARG_INDEX = 2;

  /**
   * Index of query-data argument.
   */
  private static final int QUERY_DATA_ARG_INDEX = 3;

  /**
   * List of all available algorithms.
   */
  private LinkedList<NearestNeighborSearch> algoList;

  /**
   * Creates a Benchmark command.
   */
  public Benchmark() {
    this.algoList = new LinkedList<NearestNeighborSearch>();
    this.algoList.add(new FullSearch());
    this.algoList.add(new OrchardAlgorithmus());
  }

  /**
   * @inheritDoc
   *
   * @return name
   */
  public final String getName() {
    return "benchmark";
  }

  /**
   * @inheritDoc
   *
   * @return description
   */
  public final String getDescription() {
    return "Runs a benchmark for a given algorithm with given test data.";
  }

  /**
   * @inheritDoc
   *
   * @param args arguments
   */
  public final void run(final String[] args) {
    if (args.length < this.QUERY_DATA_ARG_INDEX) {
      System.out.println("Usage: " + this.getName()
      + " ALGO TEST_DATA_FILE QUERY_DATA_FILE");
      return;
    }
    String algoName = args[this.ALGO_ARG_INDEX];
    NearestNeighborSearch nearestNeighborAlgo = null;
    for (NearestNeighborSearch algo : this.algoList) {
      if (algo.getName().equals(algoName)) {
        nearestNeighborAlgo = algo;
        break;
      }
    }
    if (nearestNeighborAlgo == null) {
      System.out.println("Unknown algorithm \"" + algoName + "\"");
      return;
    }
    String testDataFilename = args[this.TEST_DATA_ARG_INDEX];
    Vector[] testDataVectors = TestData.read(testDataFilename);
    if (testDataVectors == null) {
      System.out.println("Unable to read test-data from " + testDataFilename);
      return;
    }
    String queryDataFilename = args[this.QUERY_DATA_ARG_INDEX];
    Vector[] queryDataVectors = TestData.read(queryDataFilename);
    if (queryDataVectors == null) {
      System.out.println("Unable to read test-data from " + queryDataFilename);
      return;
    }
    if (queryDataVectors.length != 1) {
      System.out.println("QUERY_DATA_FILE should contain exactly one vector");
      return;
    }
    Vector queryVector = queryDataVectors[0];
    nearestNeighborAlgo.setVectors(testDataVectors);
    nearestNeighborAlgo.getNearestNeighbor(queryVector);
    System.out.println("Found solution.");
  }
}
