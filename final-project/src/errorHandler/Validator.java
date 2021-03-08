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
package errorHandler;
/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * The Class Validator.
 */
public class Validator {

  /**
   * Check num args.
   *
   * @param argLength the arg length
   * @param args the args
   * @return true, if successful
   */
  public static <E> boolean checkNumArgs(int argLength, E [] args) {
    if (args.length == argLength) {
      return true;
    }
    return false;
  }

  /**
   * Check file name.
   *
   * @param fileName the file name
   * @return true, if successful
   */
  public static boolean checkFileName(String fileName) {
    if (fileName.matches(".*[ /.!@#$%^&*(){}~|<>?].*")) {// be careful
      return true;
    }
    return false;
  }

  /**
   * Check quote.
   *
   * @param args the args
   * @return true, if successful
   */
  public static boolean checkQuote(String[] args) {
    int quoteCount = 0;
    for (int i = 1; i < args.length; i++) {
      quoteCount += args[i].split("\"", -1).length - 1;

    }
    if (quoteCount == 2) {
      return true;
    }
    return false;

  }

  /**
   * Checks if is equal.
   *
   * @param a the a
   * @param b the b
   * @return true, if is equal
   */
  public static <E> boolean isEqual(E a, E b) {
    return (a.equals(b)) ;
      }
}
