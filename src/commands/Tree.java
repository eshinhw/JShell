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

import errorHandler.NonvalidArgumentException;
import errorHandler.Validator;
import java.util.Map;
import data.*;

/**
 * The Class Tree.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class Tree implements Command {

  /**
   * Indentation for every sub-level directories
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
    sb.append(indentation(indent - 1));
    sb.append("  ");
    sb.append(f.getFileName());
    sb.append("\n");
  }

  /**
   * Prints the directory name.
   *
   * @param dir the dir
   * @param indent the indent
   * @param sb the sb
   */
  private static void printDirectoryName(data.Directory dir, int indent, StringBuilder sb) {

    sb.append(indentation(indent));
    sb.append(dir.getDirName());
    sb.append("\n");

    for (Map.Entry<String, File> t : dir.getFileHashMap().entrySet())
      printFileName(t.getValue(), indent + 1, sb);

    for (Map.Entry<String, Directory> d : dir.getDirectoryHashMap().entrySet()) {
      printDirectoryName(d.getValue(), indent + 1, sb);
    }

  }

  /**
   * Run.
   *
   * @param fm fileManager where the command is executed
   * @param args takes no input in
   * @return the tree
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    String output = "";
    int indent = 0;
    StringBuilder sb = new StringBuilder();
    Directory root = fm.getRoot();
    printDirectoryName(root, indent, sb);
    output = sb.toString();
    if (args.length > 1) {
      if (Validator.isEqual(args[1], ">") || Validator.isEqual(args[1], ">>")) {
        fm.redirection(fm, args, output);
        return "";
      }
    }
    if (!Validator.checkNumArgs(1, args)) {
      throw new NonvalidArgumentException("error with arguments, only 1 argument expected");
    }
    return output;
  }
}
