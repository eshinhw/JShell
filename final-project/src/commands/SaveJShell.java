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
import java.io.File;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class SaveJShell implements Command {

  @SuppressWarnings("resource")
  @Override

  /**
   * The entire state of the program is written to the file FileName
   *
   * @param fm fileManager where the command is executed
   * @param args the name of the file which will be saved
   * @return the loaded jshell
   * @throws NonvalidArgumentException the nonvalid argument exception
   */

  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    String output = "";
    if (args.length > 2)
      throw new NonvalidArgumentException("error, invalid argument for saveJShell");

    if (args.length == 1)
      throw new NonvalidArgumentException("error, file path must be provided to save session data");

    FileOutputStream fileOutStream;
    ObjectOutputStream objectOutStream;
    File fileObj;
    try {
      fileObj = new File(args[1]);
      fileOutStream = new FileOutputStream(fileObj);
    } catch (Exception e) {
      throw new NonvalidArgumentException("error, something wrong with creating file output");
    }

    try {
      objectOutStream = new ObjectOutputStream(fileOutStream);
    } catch (IOException e) {
      throw new NonvalidArgumentException("IOException, something wrong with object output");
    }

    try {
      objectOutStream.writeObject(fm);
    } catch (IOException e) {
      throw new NonvalidArgumentException("error, something wrong with saving fm object");
    }

    return output;
  }

}
