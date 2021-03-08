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

import errorHandler.NonvalidArgumentException;
import commands.History;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class HistoryTest {

  History history = new History();

  private MockFileManager MockFM;

  @Before
  /**
   * HistoryTest setup
   * 
   * @param none
   * @return none
   */
  public void setUp() {
    MockFM = new MockFileManager();
    MockFM.inputHistory.add("testcommand1");
    MockFM.inputHistory.add("testcommand2");
    MockFM.inputHistory.add("testcommand3");
    MockFM.inputHistory.add("testcommand4");
  }

  @Test
  /**
   * Test history with float
   * 
   * @param none
   * @return none
   */
  public void FloatInput() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"history", "4.20420"};
      String actualOutput = history.run(MockFM, inputArrayString);
      String expectedOutput = "error, not a valid argument";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, not a valid argument");
    }
  }

  @Test
  /**
   * Test history with string
   * 
   * @param none
   * @return none
   */
  public void StringInput() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"history", "testing"};
      String actualOutput = history.run(MockFM, inputArrayString);
      String expectedOutput = "error, not a valid argument";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, not a valid argument");
    }
  }

  @Test
  /**
   * Test history with number greater than total amount of commands
   * 
   * @param none
   * @return none
   */
  public void validinputbig() throws NonvalidArgumentException {
    String[] inputArrayString = {"history", "10"};
    MockFM.inputHistory.add("history 10");
    String actualOutput = history.run(MockFM, inputArrayString);
    String expectedOutput = "1. testcommand1\n" + "2. testcommand2\n" + "3. testcommand3\n"
        + "4. testcommand4\n" + "5. history 10\n";
    assertEquals(actualOutput, expectedOutput);
  }

  @Test
  /**
   * Test history with number less than total amount of commands
   * 
   * @param none
   * @return none
   */
  public void validinputsmall() throws NonvalidArgumentException {
    String[] inputArrayString = {"history", "2"};
    MockFM.inputHistory.add("history 2");
    String actualOutput = history.run(MockFM, inputArrayString);
    System.out.println(actualOutput);
    String expectedOutput = "4. testcommand4\n" + "5. history 2\n";
    assertEquals(actualOutput, expectedOutput);
  }

}
