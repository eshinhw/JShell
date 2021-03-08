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
import commands.LoadJShell;
import commands.SaveJShell;
import commands.Search;
import commands.Tree;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class LoadJShellTest {

  LoadJShell loadJShell = new LoadJShell();
  private MockFileManager MockFM;

  /**
   * 
   * LoadJShell command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }

  /**
   * Command without path argument
   */
  @Test
  public void noPathRprovided() {
    try {
      String[] inputArrayString = {"loadJShell"};
      String actualOutput = loadJShell.run(MockFM, inputArrayString);
      String expectedOutput = "error, file path must be provided";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, file path must be provided");
    }
  }

  /**
   * Too many arguments
   */
  @Test
  public void tooManyArguments() {
    try {
      String[] inputArrayString = {"loadJShell", "/file/path", "extra_arg"};
      String actualOutput = loadJShell.run(MockFM, inputArrayString);
      String expectedOutput = "error, too many arguments for LoadJShell";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, too many arguments for LoadJShell");
    }
  }

}
