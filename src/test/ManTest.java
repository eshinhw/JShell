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
import commands.Man;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */


public class ManTest {

  Man man = new Man();

  private MockFileManager MockFM;

  /**
   * ManTest command setup
   */
  @Before
  public void setUp() {
    // Initialize the MFS
    MockFM = new MockFileManager();
  }


  /**
   * Valid command input
   */

  @Test
  public void ValidCmd() throws NonvalidArgumentException {
    String[] inputArrayString = {"man", "pwd"};
    String actualOutput = man.run(MockFM, inputArrayString);
    String expectedOutput = "\nNAME\n\t\t" + "pwd\n" + "SYNOPSIS\n\t\t" + "pwd\n"
        + "DESCRIPTION\n\t\t" + "Print name of current working directory.\n\n";

    assertEquals(actualOutput, expectedOutput);
  }

  /**
   * Invalid command input
   */
  @Test
  public void InvalidCmd() throws NonvalidArgumentException {

    try {
      String[] inputArrayString = {"man", "abcd"};
      String actualOutput = man.run(MockFM, inputArrayString);
      String expectedOutput = "error, unable to recognize command";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, unable to recognize command");
    }
  }

  /**
   * No input
   */

  @Test
  public void Empty() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"man"};
      String actualOutput = man.run(MockFM, inputArrayString);
      String expectedOutput = "error, requires only one command argument";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error, requires only one command argument");
    }

  }


}
