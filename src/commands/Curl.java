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

import data.FileManager;
import errorHandler.NonvalidArgumentException;
import errorHandler.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * The Class Curl.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */


public class Curl implements Command {

  /**
   * Curl helper.
   *
   * @param siteName the site name
   * @return the input stream reader
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private InputStreamReader curlHelper(String siteName) throws NonvalidArgumentException {
    URL urlFile = null;
    try {
      urlFile = new URL(siteName);
    } catch (IOException e) {
      throw new NonvalidArgumentException("invalid input, URL has problems");
    } catch (Exception e) {
      throw new NonvalidArgumentException("invalid input, could not reach site");
    }
    InputStreamReader stream = null;
    try {
      stream = new InputStreamReader(urlFile.openStream());

    } catch (IOException e) {
      throw new NonvalidArgumentException("invalid input, URL has problems");
    } catch (Exception e) {
      throw new NonvalidArgumentException("invalid input, could not reach site");
    }
    return stream;
  }

  /**
   * Run.
   *
   * @param fm the fm
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    if (!Validator.checkNumArgs(2, args)) {
      throw new NonvalidArgumentException("error with arguments, only 2 arguments expected");
    }
    String output = "";
    String siteName = args[1];
    String newFile = siteName.substring(siteName.lastIndexOf('/') + 1);

    InputStreamReader stream = curlHelper(siteName);

    BufferedReader in = new BufferedReader(stream);
    String contents = "";
    String line;
    try {
      while ((line = in.readLine()) != null) {
        contents += line + "\n";
      }
    } catch (Exception e) {
      throw new NonvalidArgumentException("invalid input, could not reach site");
    }
    newFile = newFile.replace(".", "");
    fm.makeFile(newFile, contents, fm.getCurrent());
    return output;
  }
}


