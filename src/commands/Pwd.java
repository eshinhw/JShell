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
 * The Class pwd
 */
public class Pwd implements Command {


  /**
   * Print the current working directory (including the whole path)
   *
   * @param fm the fileManager where the command is executed
   * @return Whole path of current working directory (including itself)
   * @throws NonvalidArgumentException
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    // if you have many arguments and you have second argument not a >
    if (!Validator.checkNumArgs(1, args) && (!(args[1].contains(">")))) {
      throw new NonvalidArgumentException("error with arguments, invalid arguments given");
    }
    String output = "";
    Directory currentDir = fm.getCurrent();

    if (currentDir.getDirName() == "/") {
      output = "/\n";
    } else {
      output = fm.getCurrentFullPath(currentDir) + "\n";
    }

    for (int i = 0; i < args.length; i++) {
      if (args[i].contains(">")) {
        fm.redirection(fm, args, output);
        return "";
      }
    }
    return output;

  }


}
