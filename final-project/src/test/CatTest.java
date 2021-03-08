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
import org.junit.Before;
import org.junit.Test;
import errorHandler.NonvalidArgumentException;
import commands.Cat;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */


public class CatTest {

  Cat cat = new commands.Cat();
  private MockFileManager MockFM;


  /**
   * CatTest command setup
   * 
   * @param none
   * @return none
   */
  @Before
  public void setUp() {
    MockFM = new MockFileManager();

    try {
      MockFM.makeFile("fileA", "file info A", MockFM.current);
    } catch (NonvalidArgumentException e) {
      // TODO Auto-generated catch block
      fail();
    }
    try {
      MockFM.makeFile("fileB", "file info B", MockFM.current);
    } catch (NonvalidArgumentException e) {
      // TODO Auto-generated catch block
      fail();
    }
  }

  /**
   * CatTest with a single file
   * 
   * @param none
   * @return none
   * @throws NonvalidArgumentException
   */

  @Test
  public void SingleFile() throws NonvalidArgumentException {
    String[] inputArrayString = {"cat", "fileA"};
    String actualOutput = cat.run(MockFM, inputArrayString);
    String expectedOutput = "";
    assertEquals(actualOutput, expectedOutput);
  }

  /**
   * CatTest with a multiple files
   * 
   * @param none
   * @return none
   */

  @Test
  public void MultipleFile() throws NonvalidArgumentException {
    String[] inputArrayString = {"cat", "fileA", "fileB"};
    String actualOutput = cat.run(MockFM, inputArrayString);
    String expectedOutput = "";
    assertEquals(actualOutput, expectedOutput);
  }


  /**
   * CatTest with no file
   * 
   * @param none
   * @return none
   */
  @Test
  public void NoFile() {
    try {
      String[] inputArrayString = {"cat"};
      String actualOutput = cat.run(MockFM, inputArrayString);
      String expectedOutput = "Error, nonvalid input with args";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "Error, nonvalid input with args");
    }
  }

}
