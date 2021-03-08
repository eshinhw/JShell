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
import java.io.*;

/**
 * The Class LoadJShell.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class LoadJShell implements Command {


  /**
   * Load the contents of the FileName and reinitialize everything that was saved previously into
   * the FileName
   *
   * @param fm fileManager where the command is executed
   * @param args the name of the file to be loaded
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @SuppressWarnings("resource")
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    String output = "";
    if (args.length > 2)
      throw new NonvalidArgumentException("error, too many arguments for LoadJShell");

    if (args.length == 1)
      throw new NonvalidArgumentException("error, file path must be provided");

    FileInputStream fis;
    ObjectInputStream ois;

    try {
      fis = new FileInputStream(args[1]);
      ois = new ObjectInputStream(fis);
    } catch (Exception e) {
      throw new NonvalidArgumentException(
          "error, something wrong with loading previous session info");
    }

    JFileManager loaded = null;

    try {
      loaded = (JFileManager) ois.readObject();
    } catch (ClassNotFoundException e) {
      throw new NonvalidArgumentException(
          "error, something wrong with loading previous session info");
    } catch (IOException e) {
      throw new NonvalidArgumentException(
          "error, something wrong with loading previous session info");
    }
    try {
      if (loaded != null) {

        fm.setRoot(loaded.getRoot());
        fm.setCurrent(loaded.getCurrent());
        fm.mergeDirectoryStack(loaded.getDirectoryStack());
        fm.mergeInputHistory(loaded.getInputHistory());
        fis.close();
        ois.close();
        output = "previous session data is successfully loaded now.\n";
      } else {
        throw new NonvalidArgumentException(
            "error, something wrong with loading previous session info");
      }
    } catch (Exception e) {
      throw new NonvalidArgumentException(
          "error, something wrong with loading previous session info");
    }

    return output;
  }

}
