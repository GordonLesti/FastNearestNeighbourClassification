package de.dailab.fnnc.command.testdata;

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
public final class TestData {

  /**
   * Hided default constructor for App.
   */
  private TestData() {
  }

  /**
   * Writes test data.
   *
   * @param vectors vector
   * @param filename filename
   * @return success
   */
  public static boolean write(final Vector[] vectors, final String filename) {
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
      return false;
    }
    return true;
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
      return null;
    }
    return vectors;
  }
}
