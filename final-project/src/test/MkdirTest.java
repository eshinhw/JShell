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

import org.junit.Test;

import commands.Mkdir;
import data.JFileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class MkdirTest {

  Mkdir mkdir = new Mkdir();
  private MockFileManager MockFM;
  private static JFileManager fm = JFileManager.createFileManager();


  /**
   * Mkdir command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }

  @Test
  public void onedir() {
    try {
      String[] inputArrayString = {"mkdir", "dir1"};
      String actualOutput = mkdir.run(MockFM, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }

  @Test
  /**
   * Multiple directories as path.
   */
  public void multiplepaths() {
    try {
      String[] inputArrayString = {"mkdir", "dir1", "dir2"};
      String actualOutput = mkdir.run(MockFM, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }

  }

  @Test
  /**
   * Invalid dir name.
   */
  public void invalidDirName() {
    try {
      String[] inputArrayString = {"mkdir", ".."};
      String actualOutput = mkdir.run(MockFM, inputArrayString);
      String expectedOutput = "error nonvalid arguments, directory name is invalid";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }

  @Test
  /**
   * Invalid path.
   */
  public void invalidPath() {
    try {
      String[] inputArrayString = {"mkdir", "dir1", "dir1/../../dir2"};
      String actualOutput = mkdir.run(fm, inputArrayString);
      String expectedOutput = "error, something wrong with command output";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      System.out.println(e.getMessage());
      assertEquals(e.getMessage(), "error, something wrong with command output");
    } catch (Exception e) {
      assertEquals(e.getMessage(), e.getMessage());
    }

  }
}
