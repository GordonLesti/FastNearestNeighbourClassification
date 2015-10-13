package de.dailab.fnnc;

import de.dailab.fnnc.command.Command;
import de.dailab.fnnc.command.CreateTestData;
import java.util.LinkedList;
import java.util.Collection;

/**
 * Application to nur the benchmark of Fast Nearest Neighbor Search algorithms.
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
    LinkedList<Command> commandList = new LinkedList<Command>();
    commandList.add(new CreateTestData());
    if (args.length < 1) {
      System.out.println(getCommandList(commandList));
      return;
    }
    boolean commandFound = false;
    for (Command command : commandList) {
      if (command.getName().equals(args[0])) {
        command.run(args);
        commandFound = true;
        break;
      }
    }
    if (!commandFound) {
      System.out.println(getCommandList(commandList));
    }
  }

  /**
   * Returns a string that shows the known commands.
   *
   * @param commandList list of commands
   * @return list
   */
  private static String getCommandList(final Collection<Command> commandList) {
    String output = "List of known commands:\n";
    for (Command command : commandList) {
      output += command.getName();
    }

    return output;
  }
}
