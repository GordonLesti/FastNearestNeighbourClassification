package de.dailab.fnnc.command;

/**
 * Interface for a command.
 */
public interface Command {

  /**
   * Returns the name of the command.
   *
   * @return name
   */
  String getName();

  /**
   * Runs a command.
   *
   * @param args arguments
   */
  void run(String[] args);
}
