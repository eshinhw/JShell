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
import errorHandler.Validator;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

/**
 * The Class pushd.
 */
public class Pushd implements Command {

  /**
   * Saves the current working directory by pushing onto directory stack and the changes the new
   * current working directory to DIR
   *
   * @param fm the fileManager where the command is executed
   * @param args Path of directory we want to change into
   * @return New line if no error is encountered. Error if no directory is found
   * @throws NonvalidArgumentException
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {

    fm.saveCurrentToStack();

    if (args.length > 2 || Validator.isEqual(args.length, 1)) {
      throw new NonvalidArgumentException("error with arguments, only 1 arguement expected");
    }

    String path = args[1];

    try {
      Directory dirToAdd = fm.pathToCurrent(fm.getCurrent(), path);
      fm.setCurrent(dirToAdd);
    } catch (Exception e) {
      throw new NonvalidArgumentException("error with arguments, invalid directory");
    }
    return "";
  }



}
