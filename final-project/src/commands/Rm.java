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
 * The Class Rm.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class Rm implements Command {

  /**
   * Removes the input directory from the file system. Also removes all the children of the input
   * directory
   *
   * @param fm the fm
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    Directory currentDir = fm.getCurrent();
    Directory DirtoRemove;

    if (args.length != 2)
      throw new NonvalidArgumentException("error, invalid arguments count for rm command");

    if (args[1] == ".." || args[1] == ".")
      throw new NonvalidArgumentException(
          "error, invalid DIR path, can't remove parent of current dir or current dir itself");

    try {
      DirtoRemove = fm.pathToCurrent(currentDir, args[1]);
    } catch (Exception e) {
      throw new NonvalidArgumentException("error, invalid DIR path");
    }

    if (DirtoRemove == fm.getCurrent() || DirtoRemove == fm.getCurrent().getParentDir()
        || DirtoRemove == fm.getRoot()) {
      throw new NonvalidArgumentException(
          "error, invalid DIR path, the end directory is either .. or .");
    }

    String DirToRemoveName = DirtoRemove.getDirName();

    Directory ParentOfDirtoRemove = DirtoRemove.getParentDir();

    if (ParentOfDirtoRemove.containsDirectoryKey(DirToRemoveName))
      ParentOfDirtoRemove.removeDirectory(DirToRemoveName);
    else {
      throw new NonvalidArgumentException("something wrong with removing a directory in rm");
    }

    return "";
  }

}
