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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import data.Directory;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import errorHandler.Validator;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class Search implements Command {

  /**
   * Recurse file.
   *
   * @param fm the fm
   * @param dir the dir
   * @param fileName the file name
   * @return the string
   */
  private String recurseFile(FileManager fm, Directory dir, String fileName) {
    String outputString = "";


    if (dir.containsFileKey(fileName)) {
      outputString += fm.getCurrentFullPath(dir) + "/" + fileName + "\n";

    }
    for (Map.Entry<String, Directory> d : dir.getDirectoryHashMap().entrySet()) {
      outputString += recurseFile(fm, d.getValue(), fileName);
    }
    return outputString;
  }

  /**
   * Recurse directory.
   *
   * @param fm the fm
   * @param dir the dir
   * @param dirName the dir name
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private String recurseDirectory(FileManager fm, Directory dir, String dirName)
      throws NonvalidArgumentException {
    String outputString = "";

    HashMap<String, Directory> hm = dir.getDirectoryHashMap();
    Iterator hmIterator = hm.entrySet().iterator();
    if (dir.containsDirectoryKey(dirName)) {
      outputString += fm.getCurrentFullPath(dir) + "/" + dirName + "\n";
    }
    while (hmIterator.hasNext()) {
      Map.Entry mapElement = (Map.Entry) hmIterator.next();
      Directory directory = (Directory) mapElement.getValue();
      outputString += recurseDirectory(fm, directory, dirName);
    }
    return outputString;
  }



  /**
   * Search helper.
   *
   * @param args the args
   * @param len the len
   * @param fileFlag the file flag
   * @param dirFlag the dir flag
   * @return the int
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private int searchHelper(String[] args, int len, int fileFlag, int dirFlag)
      throws NonvalidArgumentException {
    for (int i = 1; i < len; i++) {
      if (args[i].equals("-type")) {
        if (Validator.isEqual(i, 1)) {
          throw new NonvalidArgumentException(
              "incorrect format with search inputs, no paths given");
        }
        if (i + 3 < len) {
          if (!args[i + 2].equals("-name")) {
            throw new NonvalidArgumentException("incorrect format with search inputs: -name");
          }
          if (args[i + 1].equals("f")) {
            fileFlag = 1;
          } else if (args[i + 1].equals("d")) {
            dirFlag = 1;
          } else {
            throw new NonvalidArgumentException(
                "incorrect format with search inputs: -type d or f");
          }
        } else {
          throw new NonvalidArgumentException("incorrect format with search inputs");
        }
      }
    }
    // check if object's Flag was set
    if (dirFlag == 0 && fileFlag == 0) {
      throw new NonvalidArgumentException("need to contain: -type f or d ");
    }
    if (fileFlag == 1) {
      return 1;
    } else if (dirFlag == 1) {
      return 2;
    }
    return 0;
  }

  /**
   * Search a given path for a file with input name
   *
   * @param fm fileManager where the command is executed
   * @param args path
   * @param redirFlag the redir flag
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private String runSearch(FileManager fm, String[] args, int redirFlag)
      throws NonvalidArgumentException {
    String output = "";
    int dirFlag = 0;
    int fileFlag = 0;
    String objectName = fm.doubleQuotetoString(args);
    int len = args.length;
    // check for correct inputs
    int flag = searchHelper(args, len, fileFlag, dirFlag);
    // check for if files and directories containing filename
    Directory currentDir = null;
    for (int i = 1; i < len; i++) {
      if (args[i].equals("-type")) {
        break;
      }
      currentDir = fm.pathToCurrent(fm.getCurrent(), args[i]);
      if (Validator.isEqual(flag, 2)) {

        output = recurseDirectory(fm, currentDir, objectName);
        if (redirFlag == 0) {
          System.out.println(output);
        } else {
          fm.redirection(fm, args, output);
        }

      } else if (Validator.isEqual(flag, 1)) {
        output = recurseFile(fm, currentDir, objectName);
        if (redirFlag == 0) {
          System.out.println(output);
        } else {
          fm.redirection(fm, args, output);
        }
      } else {
        throw new NonvalidArgumentException("invalid arguments in search");
      }
      // recurse through all subdirs of currentDir to see if there is an object with this name
    }
    if (output.isBlank()) {
      throw new NonvalidArgumentException("no such file or directory");
    }
    return "";
    // return output;
  }

  /**
   * Run.
   *
   * @param fm fileManager where the command is executed
   * @param args the path
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    if (args.length == 1) {
      throw new NonvalidArgumentException("Error, nonvalid input with args");
    }
    String output = "";
    int redirFlag = 0;
    if (fm.redirectionChecker(args) == 1) {
      redirFlag = 1;
      String outString = fm.formatRedirect(args);
      outString = outString.trim();
      String[] holderString = outString.split("\\s+");
      output = runSearch(fm, args, redirFlag);
      fm.redirection(fm, args, output);
      return "";
    }
    output = runSearch(fm, args, redirFlag);

    return output;
  }
}
