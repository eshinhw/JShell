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

import java.util.HashMap;
import java.io.*;
import data.*;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * The Class directory.
 */
@SuppressWarnings("serial")
public class Directory implements Serializable {

  /** The parent dir. */
  private Directory parentDir;

  /** The dir name. */
  private String dirName;

  /** The full dir path. */
  private String fullDirPath;

  /** The directory map. */
  private HashMap<String, Directory> directoryMap = new HashMap<String, Directory>();

  /** The file map. */
  private HashMap<String, File> fileMap = new HashMap<String, File>();

  /**
   * Gets the parent's directory
   *
   * @return The parent's directory
   */
  public Directory getParentDir() {
    return parentDir;
  }

  /**
   * Gets the parent's directory name
   *
   * @return The parent's directory name
   */

  public String getParentDirName() {
    return parentDir.getDirName();
  }

  /**
   * Gets the directory name
   *
   * @return the directory name
   */
  public String getDirName() {
    return this.dirName;
  }

  /**
   * Instantiates a new directory.
   *
   * @param nameToAdd The name of the new directory to add
   */
  public Directory(String nameToAdd) {
    this.dirName = nameToAdd;
  }


  /**
   * Instantiates a new directory.
   *
   * @param nameToAdd The name of the new directory to add
   * @param parentToAdd The parent directory to add
   */
  public Directory(String nameToAdd, Directory parentToAdd) {
    this.dirName = nameToAdd;
    this.parentDir = parentToAdd;
  }

  /**
   * Contains file key.
   *
   * @param keyName The key name
   * @return true, if successful
   */
  // checks if a directory has a file
  public boolean containsFileKey(String keyName) {
    if (fileMap.containsKey(keyName)) {
      return true;
    }
    return false;
  }

  /**
   * Contains directory key.
   *
   * @param keyName The key name
   * @return true, if successful
   */
  // checks if a directory has a subdirectory
  public boolean containsDirectoryKey(String keyName) {
    if (directoryMap.containsKey(keyName)) {
      return true;
    }
    return false;
  }

  /**
   * Gets the directory key.
   *
   * @return the dir key
   */
  public String getDirKey() {
    String out = "";
    for (String key : directoryMap.keySet()) {
      out += key + "\n";
    }
    return out;
  }

  /**
   * Gets the file key.
   *
   * @return the file key
   */
  public String getFileKey() {
    String out = "";
    for (String key : fileMap.keySet()) {
      out += key + "\n";
    }
    return out;
  }


  /**
   * Sets the directory.
   *
   * @param nameOfDir The name of the directory
   * @param dirToAdd The name of the directory to add
   */
  public void setDirectory(String nameOfDir, Directory dirToAdd) {
    directoryMap.put(nameOfDir, dirToAdd);
    dirToAdd.parentDir = this;
  }

  /**
   * Gets the directory.
   *
   * @param name The name of the directory
   * @return The directory
   */

  public Directory getDirectory(String name) {
    // get the subdirectory from current directory given name
    return directoryMap.get(name);
  }

  /**
   * Sets the file.
   *
   * @param nameOfFile The name of file
   * @param fileToAdd The file to add
   */
  // }
  public void setFile(String nameOfFile, File fileToAdd) {
    fileMap.put(nameOfFile, fileToAdd);
  }

  /**
   * Gets the file.
   *
   * @param nameOfFile The name of file
   * @return The file
   */

  public File getFile(String nameOfFile) {
    return fileMap.get(nameOfFile);
  }

  /**
   * Gets the sub-directory.
   * 
   * @param dirName The name of sub-directory
   * @return The directory
   */
  public Directory getSubdirectory(String dirName) {
    return directoryMap.get(dirName);
  }

  /**
   * Removes a sub-directory
   * 
   * @param dirName
   */
  public void removeDirectory(String dirName) {
    directoryMap.remove(dirName);
  }

  /**
   * Removes a file
   * 
   * @param fileName
   */
  public void removeFile(String fileName) {
    fileMap.remove(fileName);
  }

  /**
   * Gets a directory hashmap object
   * 
   * @return an object of directory hashmap for sub-directories
   */
  public HashMap<String, Directory> getDirectoryHashMap() {
    return this.directoryMap;
  }

  /**
   * Gets a file hashmap object
   * 
   * @return an object of file hashmap
   */
  public HashMap<String, File> getFileHashMap() {
    return this.fileMap;
  }



}
