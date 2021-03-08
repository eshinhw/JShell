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


import commands.SaveJShell;
import data.Directory;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class SaveJShellTest {

  SaveJShell saveJShell = new SaveJShell();
  private MockFileManager MockFM;

  /**
   * 
   * SaveJSHell command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }

  /**
   * Invalid file path
   */
  @Test
  public void invalidFilePath() {
    try {
      String[] inputArrayString = {"saveJShell", "..//invalid//file/path/"};
      String actualOutput = saveJShell.run(MockFM, inputArrayString);
      String expectedOutput = "error, something wrong with creating file output";
      assertEquals(actualOutput, expectedOutput);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "error, something wrong with creating file output");
    }
  }

  /**
   * No file path provided
   */
  @Test
  public void noFilePath() {
    try {
      String[] inputArrayString = {"saveJShell"};
      String actualOutput = saveJShell.run(MockFM, inputArrayString);
      String expectedOutput = "error, file path must be provided to save session data";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, file path must be provided to save session data");
    }

  }

  /**
   * Too many arguments
   */
  @Test
  public void tooManyArguments() {
    try {
      String[] inputArrayString = {"saveJShell", "/file/path", "extra_arg"};
      String actualOutput = saveJShell.run(MockFM, inputArrayString);
      String expectedOutput = "error, invalid argument for saveJShell";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, invalid argument for saveJShell");
    }
  }

}
