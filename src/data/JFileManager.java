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

import java.util.Stack;
import java.util.ArrayList;
import errorHandler.NonvalidArgumentException;
import errorHandler.Validator;
import java.io.*;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Ananda
 */
/**
 * 
 * File Manager where our commands are executed
 * 
 * @param <E>
 *
 */

// @SuppressWarnings("serial")
public class JFileManager implements FileManager, Serializable {

  private static JFileManager fm = null;
  private String fullCurrDirPath;
  private Directory root;
  private Directory current;
  private Stack<Directory> directoryStack;
  private ArrayList<String> inputHistory; // will be used for serialization
  private static final long serialVersionUID = 10L;

  /**
   * Initializes JFileManager
   */
  private JFileManager() {
    root = new Directory("/", null); // the parent directory of root should be null
    current = root; // when the program first runs, current location should be root
    directoryStack = new Stack<Directory>();
    inputHistory = new ArrayList<String>();
  }

  /**
   * Singleton Design
   * 
   * @return FileManager
   */
  public static JFileManager createFileManager() {
    if (fm == null) {
      fm = new JFileManager();
    }
    return fm;
  }

  /**
   * Adds command input from user
   * 
   * @param string of user command input
   */
  public void addInputHistory(String userInput) {
    inputHistory.add(userInput);
  }

  /**
   * Gets a list of user input history
   * 
   * @return an array list of string containing previous user inputs
   */
  public ArrayList<String> getInputHistory() {
    ArrayList<String> outputHistory = new ArrayList<String>();
    for (String c : inputHistory)
      outputHistory.add(c);
    return outputHistory;
  }


  /**
   * Merges input history from previous session with history of current session
   * 
   * @param a list of user input history from previous session
   * @return a merged list of input history from previous session and current session
   */
  public void mergeInputHistory(ArrayList<String> previous_history) {
    ArrayList<String> merged_history = new ArrayList<String>();
    for (String c : previous_history) {
      merged_history.add(c);
    }
    ArrayList<String> current_history = fm.getInputHistory();

    for (String c : current_history) {
      merged_history.add(c);
    }
    fm.inputHistory = (ArrayList<String>) merged_history;
  }

  /**
   * Merges a current directory stack with the stack from previous session
   * 
   * @param old_dirStack from previous session
   */
  public void mergeDirectoryStack(Stack<Directory> old_dirStack) {

    Stack<Directory> temp = new Stack<Directory>();
    Stack<Directory> mergedStack = new Stack<Directory>();
    Directory popped = null;

    while (!old_dirStack.isEmpty()) {
      popped = old_dirStack.pop();
      temp.push(popped);
    }

    while (!temp.isEmpty()) {
      popped = temp.pop();
      mergedStack.push(popped);
    }

    if (temp.isEmpty()) {
      while (!directoryStack.isEmpty()) {
        popped = directoryStack.pop();
        temp.push(popped);
      }
    }

    while (!temp.isEmpty()) {
      popped = temp.pop();
      mergedStack.push(popped);
    }

    this.directoryStack = mergedStack;
  }

  /**
   * Gets a directory stack for current session
   * 
   * @return a stack of directories
   */
  public Stack<Directory> getDirectoryStack() {
    return directoryStack;
  }


  /**
   * Gets the root directory of current session
   * 
   * @return root directory
   */
  public Directory getRoot() {
    return this.root;
  }

  /**
   * Sets the input root directory as the root directory of current session
   */
  public void setRoot(Directory new_root) {
    this.root = new_root;
  }


  /**
   * Gets the current directory
   * 
   * @return current directory
   */

  public Directory getCurrent() {
    return this.current;
  }

  /**
   * Sets the input directory as the new current directory
   * 
   * @param the new current directory
   */
  public void setCurrent(Directory newCurrent) {
    this.current = newCurrent;
  }

  /**
   * Get the full path of the current directory
   * 
   * @return the full path of current directory
   */
  public String getPath() {
    return this.fullCurrDirPath;
  }

  /**
   * Sets the input path as our current directory path
   * 
   * @param the new path of current directory
   */
  public void setPath(String newPath) {
    this.fullCurrDirPath = newPath;
  }

  /**
   * Gets the current full path of the directory we are in
   * 
   * @return String
   */


  public String getCurrentFullPath(Directory currentDir) {
    String path = "";
    Directory temp = currentDir;

    while (temp != null) {
      path = temp.getDirName() + path;
      if (temp.getParentDir() != null)
        path = "/" + path;
      temp = temp.getParentDir();
    }

    return path.substring(1);
  }

  /**
   * Pushes a directory to the stack of directories
   * 
   * @param Directory to push
   * @return The path of the directory stack
   */
  public void pushStack(Directory dir) {
    directoryStack.push(dir);
  }

  /**
   * Get the directory path that is at the top of the stack
   * 
   * @return The directory at the of the stack
   */
  public Directory popStack() {
    Directory tmp = directoryStack.pop();
    return tmp;
  }

  /**
   * Path to current directory
   * 
   * @param Current directory
   * @param path
   * @return Path to current directory
   */
  // give it a path, it will return dir at the path
  public Directory pathToCurrent(Directory currentDir, String path)
      throws NonvalidArgumentException {
    // check for absolute path
    if (path.contains("//")) {
      throw new NonvalidArgumentException("path doesn't exist");
    }
    if (path.length() == 1) {
      if (path.substring(0, 1).equals(".")) {
        return currentDir;
      }
      if (path.substring(0, 1).equals("/")) {
        // make current path root
        currentDir = currentToRoot(currentDir);
        return currentDir;
      }
    }
    if (path.substring(0, 1).equals("/")) {
      // make current path root
      currentDir = currentToRoot(currentDir);
      // get rid of the first"/" in the path string
      path = path.substring(1, path.length());
    }
    if (path.length() == 2) {
      if (path.substring(0, 2).equals("..")) {
        currentDir = currentDir.getParentDir();
        return currentDir;
      }
    }
    String[] pathStrings = path.split("/");
    for (int i = 0; i < pathStrings.length; i++) {
      if (pathStrings[i].equals("..")) {
        currentDir = currentDir.getParentDir();
        continue;
      }
      if (pathStrings[i].equals("."))
        continue;
      if (currentDir.containsDirectoryKey(pathStrings[i])) {
        currentDir = currentDir.getDirectory(pathStrings[i]);
      } else {
        throw new NonvalidArgumentException("path doesn't exist");
      }
    }
    return currentDir;
  }


  public boolean checkPathToDir(Directory currentDir, String path) {
    try {
      if (pathToCurrent(currentDir, path) instanceof Directory) {
        return true;
      }
    } catch (NonvalidArgumentException e) {
      return false;
    }
    return false;
  }

  /**
   * Current path to root
   * 
   * @param Current directory
   * @return Current directory
   */

  public Directory currentToRoot(Directory currentdir) {
    while (currentdir.getParentDir() != null) {
      currentdir = currentdir.getParentDir();
    }
    return currentdir;

  }

  /**
   * Save current working directory to directoryStack for pushd command
   */
  public void saveCurrentToStack() {
    directoryStack.push(this.getCurrent());
  }

  /**
   * given a path, return whether it is a file at the end of that path as a boolean
   * 
   * @param Path
   * @param Current Directory
   * @return boolean value of if the file is at the end
   * @throws NonvalidArgumentException
   */
  public boolean checkPathToFile(String path, Directory currentDir)
      throws NonvalidArgumentException {

    if (path.substring(0, 1).equals("/")) {
      currentDir = currentToRoot(currentDir);
      path = path.substring(1, path.length());
    }
    if (path.chars().filter(num -> num == '/').count() == 0) {
      return currentDir.containsFileKey(path);
    }
    String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
    String pathtofile = path.substring(0, path.lastIndexOf("/") + 1);
    Directory fileDirectory = null;
    try {
      fileDirectory = pathToCurrent(currentDir, pathtofile);
    } catch (NonvalidArgumentException e) {
      // TODO Auto-generated catch block
      throw new NonvalidArgumentException("invlaid path");
    }
    if (fileDirectory.containsFileKey(fileName)) {
      return true;
    }
    return false;
  }

  /**
   * Output the file that is input.
   *
   * @param fileName the file name
   * @param output the output
   * @param fm the fileManager where the command is executed
   * @return The string to output
   */
  public void makeFile(String fileName, String output, Directory currentDir)
      throws NonvalidArgumentException {
    if (currentDir.containsDirectoryKey(fileName)) {
      throw new NonvalidArgumentException("error, this directory name already exists");
    } else if (currentDir.containsFileKey(fileName)) {
      // file exists, append
      currentDir.getFile(fileName).setContents(output);
    } else {
      // file does not exist, make it
      File newFile = new File(fileName, currentDir);
      newFile.setContents(output);
      currentDir.setFile(fileName, newFile);
    }
  }

  /**
   * Echo helper.
   *
   * @param output the output
   * @param holderString the holder string
   * @param indexOverwrite the index overwrite
   * @param indexAppend the index append
   * @param fileName the file name
   * @param fm the fm
   * @param currentDir the current dir
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private void echoAppend(String fileName, Directory currentDir, FileManager fm, String output)
      throws NonvalidArgumentException {
    if (fileName.contains("/")) {
      String[] pathHolder = fileName.split("/");
      String newFile = pathHolder[pathHolder.length - 1];
      String argDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
      newFile = newFile.trim();
      // check if it is a path
      if (fm.pathToCurrent(currentDir, argDir) instanceof Directory) {
        // check if is a valid name for file
        if (Validator.checkFileName(newFile)) {
          throw new NonvalidArgumentException("error with input, nonvalid file name");
        }
        // get the fileName
        currentDir = fm.pathToCurrent(currentDir, argDir);
        if (currentDir.containsFileKey(newFile)) {
          output = currentDir.getFile(newFile).getFileContents() + "\n" + output;
        }
        makeFile(newFile, output, currentDir);
      } else {
        throw new NonvalidArgumentException("file path not valid");
      }
    }
    // append with relative path
    else {
      if (Validator.checkFileName(fileName)) {
        throw new NonvalidArgumentException("error with input, nonvalid file name");
      }
      if (currentDir.containsFileKey(fileName)) {
        output = currentDir.getFile(fileName).getFileContents() + "\n" + output;
      }
      makeFile(fileName, output, currentDir);
    }
  }

  /**
   * helper function to overwrite contents for echo command
   * 
   * @param fileName
   * @param fm
   * @param currentDir
   * @param output
   * @throws NonvalidArgumentException
   */
  private void echoOverwrite(String fileName, FileManager fm, Directory currentDir, String output)
      throws NonvalidArgumentException {
    if (fileName.contains("/")) {
      String[] pathHolder = fileName.split("/");
      String newFile = pathHolder[pathHolder.length - 1];
      String argDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
      newFile = newFile.trim();
      // check if it is a path
      if (fm.pathToCurrent(currentDir, argDir) instanceof Directory) {
        // check if is a valid name for file
        if (Validator.checkFileName(newFile)) {
          throw new NonvalidArgumentException("error with input, nonvalid file name");
        }
        // get the fileName
        currentDir = fm.pathToCurrent(currentDir, argDir);
        makeFile(newFile, output, currentDir);
      } else {
        throw new NonvalidArgumentException("file path not valid");
      }
    } else {
      if (Validator.checkFileName(fileName)) {
        throw new NonvalidArgumentException("error with input, nonvalid file name");
      }
      makeFile(fileName, output, currentDir);
    }
  }

  /**
   * Helper function for echo command
   * 
   * @param output
   * @param holderString
   * @param indexOverwrite
   * @param indexAppend
   * @param fileName
   * @param fm
   * @param currentDir
   * @throws NonvalidArgumentException
   */
  private void echoHelper(String output, String holderString, int indexOverwrite, int indexAppend,
      String fileName, FileManager fm, Directory currentDir) throws NonvalidArgumentException {
    if (indexOverwrite != -1 && indexAppend != -1) {
      // print to screen
      throw new NonvalidArgumentException("error with input, you have too many redirections");
    }
    // indexOverwrite is nonexistent, we have >>
    else if (indexAppend != -1) {
      fileName = holderString.substring(indexAppend + 2);
      fileName = fileName.trim();
      echoAppend(fileName, currentDir, fm, output);
    }
    // indexAppend is nonexistent, we have >
    else if (indexOverwrite != -1) {
      fileName = holderString.substring(indexOverwrite + 1);
      fileName = fileName.trim();
      echoOverwrite(fileName, fm, currentDir, output);
    }
  }

  /**
   * Function parser.
   *
   * @param output the output
   * @param indexOverwrite the index overwrite
   * @param indexAppend the index append
   * @param fileName the file name
   * @param fm the fm
   * @param currentDir the current dir
   * @param holderString the holder string
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  public void redirection(FileManager fm, String args[], String output)
      throws NonvalidArgumentException {
    String fileName = "";
    Directory currentDir;
    String holderString = "";
    for (int i = 1; i < args.length; i++) {
      holderString += args[i] + " ";
    }
    // we want to check if the user typed quotes in quotes, which is illegal
    holderString.trim();

    int indexAppend = holderString.indexOf(">>");
    int indexOverwrite = -1;
    // if >> does not exist
    if (indexAppend == -1) {
      indexOverwrite = holderString.indexOf(">");
    }
    // if fileName is path
    if (fileName.contains("/")) {
      String[] pathHolder = fileName.split("/");
      String newFile = pathHolder[pathHolder.length - 1];
      // get the fileName
      newFile = newFile.trim();
      currentDir = fm.pathToCurrent(fm.getCurrent(), fileName);
    } else {
      currentDir = fm.getCurrent();
    }
    echoHelper(output, holderString, indexOverwrite, indexAppend, fileName, fm, currentDir);
  }

  /**
   * redirection checker
   * 
   */
  public int redirectionChecker(String[] args) {
    int found = 0;
    for (int i = 0; i < args.length; i++) {
      if (args[i].contains(">")) {
        found = 1;
      }
    }
    return found;
  }

  /**
   * Format redirect
   * 
   */
  public String formatRedirect(String[] args) {
    String outputStrings = "";
    for (int i = 0; i < args.length; i++) {

      if (args[i].contains(">")) {
        int indexHolder = args[i].indexOf(">");
        String part = args[i].substring(0, indexHolder);
        part = part.trim();
        outputStrings += part;
        break;
      }

      outputStrings += " " + args[i] + " ";
    }

    return outputStrings;

  }

  /**
   * Double Quote to String
   * 
   */
  public String doubleQuotetoString(String[] args) throws NonvalidArgumentException {
    String output = "";
    int count = 0;
    for (int i = 1; i < args.length; i++) {
      output += args[i] + " ";
    }
    for (int i = 0; i < output.length(); i++) {
      if (output.charAt(i) == '\"') {
        count++;
      }
    }
    if (count != 2) {
      throw new NonvalidArgumentException("need 2 double quotes surrounding inputs");
    }
    output.trim();
    int firstQuote = output.indexOf("\"");
    int secQuote = output.indexOf("\"", firstQuote + 1);
    output = output.substring(firstQuote + 1, secQuote);
    return output;

  }
}

