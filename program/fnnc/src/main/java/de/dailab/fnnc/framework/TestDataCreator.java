package de.dailab.fnnc.framework;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutput;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import de.dailab.fnnc.distance.Vector;

/**
 * Class that creates and reads test data.
 */
public final class TestDataCreator {

  /**
   * Hided default constructor for App.
   */
  private TestDataCreator() {
  }

  /**
   * Writes test data.
   *
   * @param vectors vector
   * @param filename filename
   */
  public static void write(final Vector[] vectors, final String filename) {
    try {
      OutputStream file = new FileOutputStream(filename);
      OutputStream buffer = new BufferedOutputStream(file);
      ObjectOutput output = new ObjectOutputStream(buffer);
      try {
        output.writeObject(vectors);
      } finally {
        output.close();
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }

  /**
   * Reads test data.
   *
   * @param filename filename
   * @return vectors
   */
  public static Vector[] read(final String filename) {
    Vector[] vectors = null;
    try {
      InputStream file = new FileInputStream(filename);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input = new ObjectInputStream(buffer);
      try {
        vectors = (Vector[]) input.readObject();
      } finally {
        input.close();
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
    return vectors;
  }
}
