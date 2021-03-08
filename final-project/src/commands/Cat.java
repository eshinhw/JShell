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
 * The Class Cat.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class Cat implements Command {

  /**
   * Display the contents of FILE1 and other Files(i.e. File2 .) concatenated in the shell
   *
   * @param args A list of file names which will be output
   * @param redirectFlag the redirect flag
   * @param output the output
   * @param exceptionFlag the exception flag
   * @param fm the fm
   * @param originalArgs the original args
   * @return A string with the contents of the File(s) which are concatenated in the shell
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private String catHelper(String[] args, int redirectFlag, String output, int exceptionFlag,
      FileManager fm, String[] originalArgs) throws NonvalidArgumentException {
    for (int i = 1; i < args.length; i++) {
      if (!fm.checkPathToFile(args[i], fm.getCurrent())) {
        if (redirectFlag == 0) {
          System.out.println(output);
        } else {
          fm.redirection(fm, originalArgs, output);
        }
        throw new NonvalidArgumentException("Error, nonvalid input with file name for redirection");
      }
      if (args[i].contains(">")) {
        redirectFlag = 1;
        break;
      }
      Directory currentDir = fm.getCurrent();
      // need to check if its a file or not, otherwise give error
      try {
        if (args[i].chars().filter(num -> num == '/').count() == 0) {
          try {
            output += currentDir.getFile(args[i]).getFileContents() + "\n";
            output += "\n\n\n";
          } catch (Exception e) {
            exceptionFlag = 1;
            break;
          }
        } else {
          String arg = args[i].substring(0, args[i].lastIndexOf("/") + 1);
          String argFileName = args[i].substring(args[i].lastIndexOf("/") + 1, args[i].length());
          output += fm.pathToCurrent(currentDir, arg).getFile(argFileName).getFileContents() + "\n";
          output += "\n\n\n";
        }
      } catch (Exception e) {
        exceptionFlag = 1;
        break;
      }
    }
    return output;
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
    int exceptionFlag = 0;
    int redirectFlag = 0;
    if (args.length == 1) {
      throw new NonvalidArgumentException("Error, nonvalid input with args");
    }
    if (fm.redirectionChecker(args) == 1) {
      redirectFlag = 1;
      String outString = fm.formatRedirect(args);
      outString = outString.trim();
      String[] holderString = outString.split("\\s+");
      output = catHelper(holderString, redirectFlag, output, exceptionFlag, fm, args);
      // output = lsHelper(holderString, output, currentDir, fm, newDir, redirFlag);
      fm.redirection(fm, args, output);
      return "";
    }
    output = catHelper(args, redirectFlag, output, exceptionFlag, fm, args);
    output = output.trim();
    System.out.println(output);
    if (exceptionFlag == 1) {
      throw new NonvalidArgumentException("Error, nonvalid input with file name");
    }
    return "";
  }
}
