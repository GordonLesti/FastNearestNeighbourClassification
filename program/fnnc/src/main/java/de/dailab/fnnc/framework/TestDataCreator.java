package de.dailab.fnnc.framework;

import java.io.*;
import de.dailab.fnnc.distance.Vector;

public class TestDataCreator {

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
