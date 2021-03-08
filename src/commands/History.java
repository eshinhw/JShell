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

import java.util.ArrayList;
import errorHandler.*;
import data.*;


/**
 * The Class History.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class History implements Command {

  /** The hist. */
  private ArrayList<String> hist;

  /**
   * This command will print out recent commands, one command per line.
   *
   * @param fm the fileManager where the command is executed
   * @param splitInput Total amount of previous commands wanted to be displayed, or all the previous
   *        commands if no number is input
   * @return List of the last x previous commands given
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private String historyHelper(FileManager fm, String[] splitInput)
      throws NonvalidArgumentException {
    hist = fm.getInputHistory();
    String output = "";
    int beginningIdx = 0;
    if (splitInput.length > 2)
      throw new NonvalidArgumentException("error with arguments, only 1 argument expected");
    else if (splitInput.length == 2) {
      try {
        int argInteger = Integer.parseInt(splitInput[1]);
        beginningIdx = Math.max(hist.size() - argInteger, 0);
      } catch (Exception e) {
        throw new NonvalidArgumentException("error, not a valid argument");
      }
    }
    for (int i = beginningIdx; i < hist.size(); i++)
      output += (i + 1) + ". " + hist.get(i) + "\n";
    return output;
  }



  /**
   * Run.
   *
   * @param fm the fm
   * @param splitInput the split input
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  public String run(FileManager fm, String[] splitInput) throws NonvalidArgumentException {
    String outputString = "";
    if (fm.redirectionChecker(splitInput) == 1) {
      String outString = fm.formatRedirect(splitInput);
      outString = outString.trim();
      String[] holderString = outString.split("\\s+");
      outputString = historyHelper(fm, holderString);
      fm.redirection(fm, splitInput, outputString);
      return "";
    }
    return historyHelper(fm, splitInput);
  }
}
