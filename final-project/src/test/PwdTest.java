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
import commands.Pwd;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class PwdTest {

  Pwd pwd = new Pwd();
  private MockFileManager MockFM;

  /**
   * Pwd command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }

  /**
   * 
   * Extra parameter
   */
  @Test
  public void extraparam() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"pwd", "test"};
      String actualOutput = pwd.run(MockFM, inputArrayString);
      String expectedOutput = "error with arguments, invalid arguments given";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, invalid arguments given");
    }
  }

  /**
   * 
   * Testing the pwd method. Does not take any user input
   */
  @Test
  public void test() throws NonvalidArgumentException {
    String[] inputArrayString = {"pwd"};
    MockFM.currentDirectory = "/";

    String actualOutput = pwd.run(MockFM, inputArrayString);

    assertEquals(actualOutput, "/\n");
  }

  // assertEquals(pwd.run(MockFM, ""), "\n");
  // MockFM.currentDirectory = "test 1";
  // assertEquals(pwd.run(MockFM, ""), "test 1\n");
  // assertEquals(pwd.run(MockFM, "some params"), "test 1\n");
  // }

}
