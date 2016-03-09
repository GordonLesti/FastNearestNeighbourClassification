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
   * Returns the description of the command.
   *
   * @return description
   */
  String getDescription();

  /**
   * Runs a command.
   *
   * @param args arguments
   */
  void run(String[] args);
}
