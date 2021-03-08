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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.Cp;
import data.Directory;
import data.File;
import errorHandler.NonvalidArgumentException;
import data.JFileManager;


/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class CpTest {

  Cp cp = new Cp();
  private static JFileManager fm = JFileManager.createFileManager();

  /**
   * Cp command setup
   * 
   * @param none
   * @return none
   * @throws NonvalidArgumentException
   */
  @Before
  public void setUp() throws NonvalidArgumentException {
    Directory currentDirectory = fm.getCurrent();
    Directory dir1 = new Directory("dir1", currentDirectory);
    currentDirectory.setDirectory("dir1", dir1);
    Directory dir2 = new Directory("dir2", currentDirectory);
    currentDirectory.setDirectory("dir2", dir2);
    Directory childOfDir1 = new Directory("childOfDir1", currentDirectory.getDirectory("dir1"));
    currentDirectory.getDirectory("dir1").setDirectory("childOfDir1", childOfDir1);
    fm.makeFile("file1", "content", currentDirectory);
    fm.makeFile("file2", "content2", currentDirectory);

  }

  /**
   * Copy one directory to existing dir
   * 
   */
  @Test
  public void copyOneDirToExistingDir() {
    try {
      String[] inputArrayString = {"cp", "dir1", "dir2"};
      String actualOutput = cp.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }

  /**
   * Copy one directory to new dir
   * 
   */
  @Test
  public void copyOneDirToNewDir() {
    try {
      String[] inputArrayString = {"cp", "dir1", "dir5"};
      cp.run(fm, inputArrayString);
      Boolean actualBoolean = fm.checkPathToDir(fm.getCurrent(), "/dir5/dir1");
      Boolean expectedBoolean = true;
      assertEquals(actualBoolean, expectedBoolean);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "");
    }
  }

  /**
   * Copy one parent dir to child dir
   * 
   */
  @Test
  public void copyParentToChild() {
    try {
      String[] inputArrayString = {"cp", "dir1", "childOfDir1"};
      String actualOutput = cp.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {

      assertEquals(e.getMessage(), "old path is parent of new path please try again");

    }
  }

  /**
   * Copy one file to dir
   * 
   */
  @Test
  public void copyFiletoDir() {
    try {
      String[] inputArrayString = {"cp", "file1", "dir1"};
      String actualOutput = cp.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "");
    }
  }

  /**
   * Copy one dir to file
   * 
   */
  @Test
  public void copyDirToFile() {
    try {
      String[] inputArrayString = {"cp", "dir1", "fi1e1"};
      String actualOutput = cp.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "");
    }
  }

  /**
   * Copy one file to file
   * 
   */
  @Test
  public void copyFileToFile() {
    try {
      String[] inputArrayString = {"cp", "file2", "fi1e1"};
      String actualOutput = cp.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "");
    }
  }


}
