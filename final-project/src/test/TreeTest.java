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
import commands.Tree;
import commands.SaveJShell;
import commands.Search;
import data.Directory;
import data.FileManager;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class TreeTest {

  Tree tree = new Tree();
  private MockFileManager MockFM;

  /**
   * 
   * Tree command set up
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
  }

  /**
   * No Input
   * 
   * @throws NonvalidArgumentException
   */
  @Test
  public void NoInput() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"tree", "test"};
      String actualOutput = tree.run(MockFM, inputArrayString);
      String expectedOutput = "error with arguments, only 1 argument expected";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, only 1 argument expected");
    }
  }

  /**
   * Valid Input
   * 
   * @throws NonvalidArgumentException
   */

  @Test
  public void ValidInput() throws NonvalidArgumentException {
    String[] inputArrayString = {"tree"};
    String actualOutput = tree.run(MockFM, inputArrayString);
    String expectedOutput = "/\n";
    assertEquals(actualOutput, expectedOutput);
  }


  /**
   * Extra arguments put in
   */
  @Test
  public void extraArg() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"tree", "test"};
      String actualOutput = tree.run(MockFM, inputArrayString);
      String expectedOutput = "error with arguments, only 1 argument expected";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, only 1 argument expected");
    }
  }


  @Test
  public void testwithdir() throws NonvalidArgumentException {
    Directory temp1 = new Directory("temp1", MockFM.getCurrent());
    MockFM.getCurrent().setDirectory("temp1", temp1);
    String[] inputArrayString = {"tree"};
    String actualOutput = tree.run(MockFM, inputArrayString);
    String expectedOutput = "/\n" + "  temp1\n";
    assertEquals(actualOutput, expectedOutput);
  }

}
