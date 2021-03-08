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
import commands.Popd;
import commands.Pushd;
import data.Directory;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class PopdTest {

  Popd popd = new Popd();
  Pushd pushd = new Pushd();

  private MockFileManager MockFM;


  @Before
  /**
   * PopdTest command setup
   */
  public void setUp() {
    // Initialize the MFS
    MockFM = new MockFileManager();
  }


  @Test
  /**
   * Extra argument in popd
   */
  public void extraArg() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"popd", "test"};
      String actualOutput = popd.run(MockFM, inputArrayString);
      String expectedOutput = "error, popd doesn't need extra argument.";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, popd doesn't need extra argument.");
    }
  }

  @Test
  /**
   * Empty stack
   */
  public void empty() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"popd"};
      String actualOutput = popd.run(MockFM, inputArrayString);
      String expectedOutput = "error, could not make directory";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, could not make directory");
    }
  }

  @Test
  /**
   * Push after pop
   */
  public void popPush() {
    try {
      Directory temp1 = new Directory("temp1", MockFM.getCurrent());
      MockFM.getCurrent().setDirectory("temp1", temp1);
      String[] inputArrayString = {"pushd", "./temp1"};
      pushd.run(MockFM, inputArrayString);
      String[] popdir = {"popd"};
      assertEquals("", popd.run(MockFM, popdir));
      // Check if its the same directory thats pushed
      assertEquals(MockFM.getCurrent(), MockFM.popStack());
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }


}
