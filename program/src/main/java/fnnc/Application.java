package fnnc;

import fnnc.algo.AnnulusMethod;
import fnnc.algo.FastNearestNeighbourClassificator;
import fnnc.algo.FullSearch;
import fnnc.algo.OrchardsAlgorithm;
import fnnc.model.point2ddouble.DistanceCalculator;

import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Random;

public class Application {

  /**
   * The entry point of the application.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println(getUsage());
      return;
    }
    String filename = args[0];
    LinkedList<LinkedList<Point2D.Double>> problems = readProblemsFromFile(filename);
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> algos =
        createAlgos(distanceCalculator);
    if (problems == null) {
      int problemSize = 1000;
      int problemCount = 1000;
      if (args.length > 1) {
        problemSize = Integer.parseInt(args[1]);
      }
      if (args.length > 2) {
        problemCount = Integer.parseInt(args[2]);
      }
      problems = createProblems(problemSize, problemCount);
      writeProblemsToFile(filename, problems);
    }
    System.out.println(runTests(distanceCalculator, algos, problems));
  }

  private static String runTests(
      DistanceCalculator distanceCalculator,
      LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> algos,
      LinkedList<LinkedList<Point2D.Double>> problems
  ) {
    double[][] result = new double[2][algos.size()];
    for (LinkedList<Point2D.Double> problem : problems) {
      Point2D.Double query = problem.poll();
      int counter = 0;
      Point2D.Double[] checkResult = new Point2D.Double[algos.size()];
      for (FastNearestNeighbourClassificator<Point2D.Double, Double> algo : algos) {
        algo.preProcessing(problem);
        result[0][counter] += distanceCalculator.reset();
        checkResult[counter] = algo.calculateNearestNeighbour(query);
        result[1][counter] += distanceCalculator.reset();
        counter++;
      }
      if (!checkResults(checkResult)) {
        return "ERROR";
      }
    }
    String resultString = "Size: " + problems.get(0).size() + "\nCount: " + problems.size() + "\n";
    for (int i = 0; i < algos.size(); i++) {
      double preProcessingCount = result[0][i] / problems.size();
      double mainProcessingCount = result[1][i] / problems.size();
      String algoName = algos.get(i).getClass().getName();
      resultString += algoName + ":\n\tPreProcessing: " + preProcessingCount
          + "\n\tMainProcessing: " + mainProcessingCount + "\n";
    }
    return resultString;
  }

  private static LinkedList<LinkedList<Point2D.Double>> readProblemsFromFile(String filename) {
    try (
      InputStream file = new FileInputStream(filename);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input = new ObjectInputStream(buffer);
    ) {
      @SuppressWarnings("unchecked")
      LinkedList<LinkedList<Point2D.Double>> problems =
          (LinkedList<LinkedList<Point2D.Double>>) input.readObject();
      return problems;
    } catch (Exception e) {
      return null;
    }
  }

  private static void writeProblemsToFile(
      String filename,
      LinkedList<LinkedList<Point2D.Double>> problems
  ) {
    try (
      OutputStream file = new FileOutputStream(filename);
      OutputStream buffer = new BufferedOutputStream(file);
      ObjectOutput output = new ObjectOutputStream(buffer);
    ) {
      output.writeObject(problems);
    } catch (IOException e) {
      System.out.println("Can not write to file " + filename);
    }
  }

  private static LinkedList<LinkedList<Point2D.Double>> createProblems(
      int problemSize,
      int problemCount
  ) {
    LinkedList<LinkedList<Point2D.Double>> problems = new LinkedList<LinkedList<Point2D.Double>>();
    Random rand = new Random();
    for (int i = 0; i < problemCount; i++) {
      LinkedList<Point2D.Double> problem = new LinkedList<Point2D.Double>();
      for (int j = 0; j < problemSize + 1; j++) {
        problem.add(new Point2D.Double(rand.nextDouble(), rand.nextDouble()));
      }
      problems.add(problem);
    }
    return problems;
  }

  private static LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> createAlgos(
      DistanceCalculator distanceCalculator
  ) {
    LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>> algos =
        new LinkedList<FastNearestNeighbourClassificator<Point2D.Double, Double>>();
    algos.add(new FullSearch<Point2D.Double, Double>(distanceCalculator));
    algos.add(new OrchardsAlgorithm<Point2D.Double>(distanceCalculator));
    algos.add(new AnnulusMethod<Point2D.Double>(distanceCalculator));

    return algos;
  }

  private static boolean checkResults(Point2D.Double[] result) {
    for (int i = 0; i < result.length - 1; i++) {
      if (result[i] != result[i + 1]) {
        return false;
      }
    }
    return true;
  }

  private static String getUsage() {
    return "Usage: java -jar fnnc.jar filename [size] [count]\n\tfilename    stores & loads the pr"
        + "oblems in this file\n\tsize        size of the problems, default is 1000\n\tcount      "
        + " how many problems will be generated, default is 1000\n";
  }
}