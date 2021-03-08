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
package data;

import java.io.*;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * The Class file.
 */
@SuppressWarnings("serial")
public class File implements Serializable {

  /** The file directory. */
  private Directory fileDirectory;

  /** The file name. */
  private String fileName;

  /** The file contents. */
  private String fileContents;

  /**
   * Gets the file directory.
   *
   * @return the file directory
   */
  public Directory getFileDirectory() {
    return fileDirectory;
  }

  /**
   * Gets the file name.
   *
   * @return the file name
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Gets the file contents.
   *
   * @return the file contents
   */
  public String getFileContents() {
    return fileContents;
  }

  /**
   * Sets the contents.
   *
   * @param toWrite the new contents
   */
  public void setContents(String toWrite) {
    this.fileContents = toWrite;
  }

  /**
   * Sets the file name.
   *
   * @param fileName the new file name
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Append contents to the file
   *
   * @param toAdd String to append
   */
  // used by echoAppend to add to a file
  public void appendContents(String toAdd) {
    this.fileContents = getFileContents() + "\n" + toAdd;
  }


  /**
   * Retrieve full path.
   *
   * @return the string
   */
  public String retrieveFullPath() {
    String path = "";
    Directory temp = this.getFileDirectory();
    path = this.getFileName() + path;

    while (temp != null) {

      if (temp.getDirName() == "/") {
        path = temp.getDirName() + path;
      } else {
        path = temp.getDirName() + path;
        path = "/" + path;
      }

      temp = temp.getParentDir();
    }

    return path + '\n';
  }


  /**
   * Instantiates a new file.
   *
   * @param fileName the file name
   * @param dir the directory
   */
  // constructor
  public File(String fileName, Directory dir) {
    this.fileName = fileName;
    this.fileDirectory = dir;
  }



}
