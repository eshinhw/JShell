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
import errorHandler.*;
import java.util.*;


/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * The Class ls, lists contents in a directory or file.
 */
public class Ls implements Command {

  /**
   * Run.
   *
   * @param args The path of the file or directory
   * @param output the output
   * @param currentDir the current dir
   * @param fm the fm
   * @param newDir the new dir
   * @param redirFlag the redir flag
   * @return Contents of the directory or the contents of the file or a suitable error message
   * @throws NonvalidArgumentException the nonvalid argument exception
   */

  public String lsHelper(String[] args, String output, Directory currentDir, FileManager fm,
      Directory newDir, int redirFlag) throws NonvalidArgumentException {
    // no path is given, ls
    // current path
    if (args.length == 1) {
      output += currentDir.getDirKey();
      output += currentDir.getFileKey();
    } else {
      try {
        for (int i = 1; i < args.length; i++) {
          // check if the given path leads to a file
          if (fm.checkPathToFile(args[i], currentDir)) {
            output += args[i] + "\n";
            continue;
          } else {
            newDir = fm.pathToCurrent(currentDir, args[i]);
            output += args[i] + ":" + "\n";
            output += newDir.getFileKey();
            output += newDir.getDirKey();
          }
        }
      } catch (Exception e) {
        if (redirFlag == 0) {
          System.out.println(output);
          throw new NonvalidArgumentException("you have a nonvalid input, could not find the path");
        } else {
          System.out.println("you have a nonvalid input, could not find the path");
        }
      }
    }
    return output;
  }

  /**
   * Indentation.
   *
   * @param indent the indent
   * @return the string
   */
  private static String indentation(int indent) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < indent; i++) {
      sb.append("  ");
    }
    return sb.toString();
  }

  /**
   * Prints the file name.
   *
   * @param f the f
   * @param indent the indent
   * @param sb the sb
   */
  private static void printFileName(data.File f, int indent, StringBuilder sb) {
    sb.append(indentation(indent));
    sb.append("  ");
    sb.append(f.getFileName());
    sb.append("\n");
  }

  /**
   * Recursive option.
   *
   * @param dir the dir
   * @param indent the indent
   * @param sb the sb
   */
  private void recursiveOption(Directory dir, int indent, StringBuilder sb) {
    sb.append(indentation(indent));
    sb.append(dir.getDirName());
    sb.append("/");
    sb.append("\n");

    for (Map.Entry<String, File> t : dir.getFileHashMap().entrySet())
      printFileName(t.getValue(), indent + 1, sb);

    for (Map.Entry<String, Directory> d : dir.getDirectoryHashMap().entrySet()) {
      recursiveOption(d.getValue(), indent + 1, sb);
    }
  }

  /**
   * Run.
   *
   * @param fm the fm
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    String output = "";
    int countGivenPath = args.length - 2;
    Directory currentDir = fm.getCurrent();
    Directory newDir = null;
    // start recursive version from here
    if (args.length > 1 && args[1].equals("-R")) {
      // iterate over each path provided after -R option
      try {
        for (int i = 2; i < countGivenPath + 2; i++) {
          newDir = fm.pathToCurrent(currentDir, args[i]);
          int indent = 0;
          StringBuilder sb = new StringBuilder();
          recursiveOption(newDir, indent, sb);
          output += sb.toString();
          output += "=========================\n";
        }
        return output;
      } catch (Exception e) {
        throw new NonvalidArgumentException("one of the given path is not correct.");
      }
    }
    int redirFlag = 0;
    if (fm.redirectionChecker(args) == 1) {
      redirFlag = 1;
      String outString = fm.formatRedirect(args);
      outString = outString.trim();
      String[] holderString = outString.split("\\s+");
      output = lsHelper(holderString, output, currentDir, fm, newDir, redirFlag);
      fm.redirection(fm, args, output);
      return "";
    }
    return lsHelper(args, output, currentDir, fm, newDir, redirFlag);
  }

}
