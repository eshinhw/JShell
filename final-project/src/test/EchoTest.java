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
import org.junit.Before;
import commands.Echo;
import errorHandler.NonvalidArgumentException;


/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class EchoTest {

  Echo echo = new commands.Echo();
  private MockFileManager MockFM;

  /**
   * EchoTest command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }


  
  /**
   * EchoTest with an invalid input
   */
  @Test
  public void NoQuotes() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"echo", "testing"};
      String actualOutput = echo.run(MockFM, inputArrayString);
      String expectedOutput = "hi\n";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with input, you have an extra argument");
    }
  }


  
  /**
   * EchoTest with valid input
   */
  @Test
  public void ValidInput() throws NonvalidArgumentException {
    String[] inputArrayString1 = {"echo", "\"testing\""};
    String actualOutput1 = echo.run(MockFM, inputArrayString1);
    String expectedOutput1 = "testing\n";
    assertEquals(actualOutput1, expectedOutput1);
  }
}
