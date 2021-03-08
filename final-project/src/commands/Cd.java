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

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class Cd implements Command {

  /**
   * Change directory to DIR, which may be relative to the current directory or may be a full path.
   * 
   * As with Unix, .. means a parent directory and/a. means the current directory. The directory
   * must be /, the forward slash. The foot of the file system is a single slash: /.
   *
   * @param fm the fileManager where the command is executed
   * @param args The name of the directory which will be our new path
   * @return A string of a new line, meaning no errors were encountered
   * @throws NonvalidArgumentException
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    if (args.length != 2) {
      throw new NonvalidArgumentException("error with arguments, only 2 arguments expected");
    }

    for (int i = 0; i < args.length; i++) {
      if (args[i].contains("//")) {
        throw new NonvalidArgumentException("error with arguments, you can not have //");
      }
    }

    Directory currentDir = fm.getCurrent();

    try {
      if (currentDir.getDirName().equals("/") && args[1].equals("..")) {
        throw new NonvalidArgumentException("error with arguments, could not go into directory");
      }
      currentDir = fm.pathToCurrent(currentDir, args[1]);
      fm.setCurrent(currentDir);
    } catch (Exception e) {
      throw new NonvalidArgumentException("error with arguments, could not find directory");
    }
    return "";
  }


}
