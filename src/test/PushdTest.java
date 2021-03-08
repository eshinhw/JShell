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
import commands.Pushd;
import commands.Popd;
import data.Directory;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class PushdTest {

  Pushd pushd = new Pushd();
  Popd popd = new Popd();
  private MockFileManager MockFM;


  @Before
  /**
   * 
   * PushdTest command set up
   *
   */

  public void setUp() {
    // Initialize the MFS
    MockFM = new MockFileManager();
    Directory temp1 = new Directory("temp1", MockFM.getCurrent());
    MockFM.getCurrent().setDirectory("temp1", temp1);
  }


  /**
   * No argument provided in pushd
   */
  @Test
  public void noArg() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"pushd"};
      String actualOutput = pushd.run(MockFM, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, only 1 arguement expected");
    }
  }


  /**
   * Pushing to a directory that does not exist
   */
  @Test
  public void pushToNonExistingDir() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"pushd", "./nonExistDir"};
      String actualOutput = pushd.run(MockFM, inputArrayString);
      assertEquals(actualOutput, "");
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, invalid directory");
    }
  }


  /**
   * Push of a directory.
   */
  @Test
  public void push() {
    try {
      String[] inputArrayString = {"pushd", "./temp1"};
      String actualOutput = pushd.run(MockFM, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "No error should be thrown");
    }
  }

}
