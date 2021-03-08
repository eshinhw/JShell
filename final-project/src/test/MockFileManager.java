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
package test;

import java.util.ArrayList;
import java.util.Stack;

import data.Directory;
import data.File;
import data.FileManager;
import data.JFileManager;
import errorHandler.NonvalidArgumentException;

// TODO: Auto-generated Javadoc
/**
 * The Class MockFileManager.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class MockFileManager implements FileManager {

  /** The files. */
  public java.util.HashMap<String, String> files;

  /** The directories. */
  public java.util.HashMap<String, Directory> directories;

  /** The history. */
  public ArrayList<String> history;

  /** The current directory. */
  public String currentDirectory;

  private static MockFileManager MockFM = null;
  /** The full curr dir path. */
  public String fullCurrDirPath = "/";

  /** The root. */
  public Directory root;

  /** The current. */
  public Directory current;

  /** The directory stack. */
  public Stack<Directory> directoryStack;

  /** The input history. */
  public ArrayList<String> inputHistory; // will be used for serialization


  /**
   * Initializes the attributes.
   */

  public MockFileManager() {
    directories = new java.util.HashMap<String, Directory>();
    directories.put("", new data.Directory(""));
    directories.put("a", new data.Directory("a"));
    files = new java.util.HashMap<String, String>();
    history = new ArrayList<String>();
    fullCurrDirPath = "/";
    root = new Directory("/", null); // the parent directory of root should be null
    current = root; // when the program first runs, current location should be root
    directoryStack = new Stack<Directory>();
    inputHistory = new ArrayList<String>();

  }

  public void setRoot(Directory new_root) {
    this.root = new_root;
  }

  /**
   * Gets the current.
   *
   * @return the current
   */
  @Override
  public Directory getCurrent() {
    return current;

  }

  /**
   * Sets the current.
   *
   * @param newCurrent the new current
   */
  @Override
  public void setCurrent(Directory newCurrent) {
    // this.currentDirectory=newCurrent;

  }

  /**
   * Gets the path.
   *
   * @return the path
   */
  @Override
  public String getPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Sets the path.
   *
   * @param newPath the new path
   */
  @Override
  public void setPath(String newPath) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the current full path.
   *
   * @return the current full path
   */
  public String getCurrentFullPath() {
    return fullCurrDirPath;
  }

  /**
   * Push stack.
   *
   * @param dir the dir
   */
  public void pushStack(Directory dir) {
    directoryStack.push(dir);

  }

  /**
   * Pop stack.
   *
   * @return the directory
   */
  @Override
  public Directory popStack() {
    Directory tmp = directoryStack.pop();
    return tmp;
  }

  /**
   * Path to current.
   *
   * @param currentdir the currentdir
   * @param path the path
   * @return the directory
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public Directory pathToCurrent(Directory currentdir, String path)
      throws NonvalidArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Current to root.
   *
   * @param currentdir the currentdir
   * @return the directory
   */
  @Override
  public Directory currentToRoot(Directory currentdir) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save current to stack.
   */
  @Override
  public void saveCurrentToStack() {
    // TODO Auto-generated method stub

  }

  /**
   * Check path to file.
   *
   * @param path the path
   * @param currentDir the current dir
   * @return true, if successful
   */
  @Override
  public boolean checkPathToFile(String path, Directory currentDir) {
    // TODO Auto-generated method stub
    return currentDir.containsFileKey(path);

    // return false;
  }


  /**
   * Redirection.
   *
   * @param fm the fm
   * @param args the args
   * @param output the output
   */
  @Override
  public void redirection(FileManager fm, String[] args, String output) {
    // TODO Auto-generated method stub

  }


  /**
   * Gets the input history.
   *
   * @return the input history
   */
  @Override
  public ArrayList<String> getInputHistory() {
    ArrayList<String> outputHistory = new ArrayList<String>();
    for (String c : inputHistory)
      outputHistory.add(c);
    return outputHistory;
  }


  /**
   * Make file.
   *
   * @param fileName the file name
   * @param output the output
   * @param currentDir the current dir
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public void makeFile(String fileName, String output, Directory currentDir)
      throws NonvalidArgumentException {
    File newFile = new File(fileName, currentDir);
    newFile.setContents(output);
    currentDir.setFile(fileName, newFile);
    // TODO Auto-generated method stub

  }


  // @Override
  // public void setDirectoryStack(Stack<Directory> directoryStack) {
  // // TODO Auto-generated method stub
  //
  // }


  /**
   * Merge input history.
   *
   * @param inputHistory the input history
   */
  @Override
  public void mergeInputHistory(ArrayList<String> previous_history) {
    ArrayList<String> merged_history = new ArrayList<String>();
    for (String c : previous_history) {
      merged_history.add(c);
    }
    ArrayList<String> current_history = MockFM.getInputHistory();

    for (String c : current_history) {
      merged_history.add(c);
    }
    MockFM.inputHistory = (ArrayList<String>) merged_history;

  }


  /**
   * Adds the input history.
   *
   * @param userInput the user input
   */
  @Override
  public void addInputHistory(String userInput) {
    inputHistory.add(userInput);

  }


  // @Override
  // public void updateInputHistory(ArrayList<String> previous_history) {
  // // TODO Auto-generated method stub
  // return;
  // }


  /**
   * Gets the directory stack.
   *
   * @return the directory stack
   */
  @Override
  public Stack<Directory> getDirectoryStack() {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Gets the current full path.
   *
   * @param currentDir the current dir
   * @return the current full path
   */
  @Override
  public String getCurrentFullPath(Directory currentDir) {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Redirection checker.
   *
   * @param args the args
   * @return the int
   */
  @Override
  public int redirectionChecker(String[] args) {
    return 0;
  }


  /**
   * Format redirect.
   *
   * @param args the args
   * @return the string
   */
  @Override
  public String formatRedirect(String[] args) {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Gets the root.
   *
   * @return the root
   */
  @Override
  public Directory getRoot() {
    // TODO Auto-generated method stub
    return root;
  }


  /**
   * Check path to dir.
   *
   * @param currentdir the currentdir
   * @param string the string
   * @return true, if successful
   */
  @Override
  public boolean checkPathToDir(Directory currentdir, String string) {
    // TODO Auto-generated method stub
    return false;
  }


  /**
   * Double quoteto string.
   *
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String doubleQuotetoString(String[] args) throws NonvalidArgumentException {
    String output = "fileA";
    return output;
  }


  /**
   * Merge directory stack.
   *
   * @param new_dirStack the new dir stack
   */
  @Override
  public void mergeDirectoryStack(Stack<Directory> new_dirStack) {
    // TODO Auto-generated method stub

  }



}
