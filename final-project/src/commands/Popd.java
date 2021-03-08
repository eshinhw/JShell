// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: wangj930
// UT Student #: 1006390031
// Author: Junheng Wang
//
// Student2:
// UTORID user_name: anadaeth
// UT Student #: 1003171907
// Author: Ethan Anada
//
// Student3:
// UTORID user_name: shinhy22
// UT Student #: 997743615
// Author: Hyun Woo (Eddie) Shin
//
// Student4:
// UTORID user_name: chouivan
// UT Student #: 1003599732
// Author: Ivanhoe Chou
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package commands;

import data.*;
import errorHandler.NonvalidArgumentException;
import java.util.EmptyStackException;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class Popd implements Command {

  /**
   * Removes the top most directory from the directory stack and makes it the current working
   * directory. If there is no directory onto the stack, then give appropriate error message
   *
   * @param fm the fileManager where the command is executed
   * @param args No input required
   * @return New line if no error is encountered. Error otherwise if directory is not found or stack
   *         is empty
   * @throws NonvalidArgumentException
   */

  public String run(data.FileManager fm, String[] args) throws NonvalidArgumentException {
    String output = "";

    if (args.length > 1)
      throw new NonvalidArgumentException("error, popd doesn't need extra argument.");

    try {
      Directory dir_popped = fm.popStack();
      fm.setCurrent(dir_popped);
    } catch (EmptyStackException e) {
      throw new NonvalidArgumentException("error, could not make directory");
    }

    return output;
  }
}
