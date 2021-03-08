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
import errorHandler.*;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * The Class mkdir.
 */
public class Mkdir implements Command {

  /**
   * Create directories, each of which may be relative to the current directory or may be a full
   * path.
   *
   * @param pathInput the path input
   * @param currentDir the current dir
   * @throws NonvalidArgumentException the nonvalid argument exception
   */

  private void mkdirHelperRealPath(FileManager fm, String pathInput, Directory currentDir)
      throws NonvalidArgumentException {

    if (Validator.checkFileName(pathInput)) {
      throw new errorHandler.NonvalidArgumentException(
          "error nonvalid arguments, directory name is invalid");
    }
    Directory tempDirectory = new Directory(pathInput, currentDir);
    if (currentDir.containsDirectoryKey(pathInput) || fm.checkPathToFile(pathInput, currentDir)) {
      throw new NonvalidArgumentException("error, this directory or file name already exists");
    } else {
      try {
        currentDir.setDirectory(pathInput, tempDirectory);
      } catch (Exception e) {
        throw new errorHandler.NonvalidArgumentException(
            "error, could not make directory from given path");
      }
    }

  }

  /**
   * @param fm the fileManager where the command is executed
   * @param args The directories to be created
   * @return A string of a new line, meaning no errors were encountered.
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    Directory currentDir = fm.getCurrent();
    if (args.length < 1) {
      // return "error, you do not have at least 1 directorie as arguments\n";
      throw new errorHandler.NonvalidArgumentException(
          "error with arguments, at least 1 arguments expected");
    } else {
      for (int i = 1; i < args.length; i++) {
        // if argument is to make a directory in the current directory
        if (args[i].chars().filter(num -> num == '/').count() == 0) {
          mkdirHelperRealPath(fm, args[i], currentDir);
        } else {
          // whole path before directory
          String arg = args[i].substring(0, args[i].lastIndexOf("/") + 1);
          // directory name
          String argDirName = args[i].substring(args[i].lastIndexOf("/") + 1, args[i].length());
          if (Validator.checkFileName(argDirName)) {
            throw new errorHandler.NonvalidArgumentException("error, nonvalid file name was given");
          }
          Directory tempDirectory = new Directory(argDirName, fm.pathToCurrent(currentDir, arg));
          Directory parentDirOfArg = fm.pathToCurrent(currentDir, arg);
          if (parentDirOfArg.containsDirectoryKey(argDirName)) {
            throw new NonvalidArgumentException("error, this directory exists");
          } else {
            try {
              fm.pathToCurrent(currentDir, arg).setDirectory(argDirName, tempDirectory);
            } catch (Exception e) {
              throw new errorHandler.NonvalidArgumentException(
                  "error with arguments, nonvalid path");
            }
          }
        }
      }
      return "";
    }
  }
}
