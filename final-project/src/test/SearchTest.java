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
import java.lang.reflect.*;

import org.junit.Test;
import commands.Search;
// import commands.Rm;
import commands.SaveJShell;
import errorHandler.NonvalidArgumentException;
import org.junit.Before;

import data.*;
// TODO: Auto-generated Javadoc

/**
 * The Class SearchTest.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class SearchTest {

  /** The search. */
  private Search search = new Search();

  /** The fm. */
  private static JFileManager fm = JFileManager.createFileManager();

  /**
   * Search command set up.
   *
   * @throws Exception the exception
   * @throws SecurityException the security exception
   */

  public void setUp() throws Exception {}

  /**
   * Test with a single file.
   *
   * @return none
   * @throws NonvalidArgumentException the nonvalid argument exception
   */

  @Test
  public void SingleFile() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"search", "/", "-type", "f", "-name", "\"fileA\""};
      String fileName = "fileA";
      Directory currentDir = fm.getCurrent();
      String output = "hello";
      fm.makeFile(fileName, output, currentDir);
      Directory value = new Directory("FileA");
      String actualOutput = search.run(fm, inputArrayString);
      String expectedOutput = "";

      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }

  /**
   * Single dir.
   *
   * @throws Exception the exception
   */

  @Test
  public void SingleDir() throws Exception {
    try {
      String[] inputArrayString = {"search", "/", "-type", "d", "-name", "\"temp1\""};
      Directory temp1 = new Directory("temp1", fm.getCurrent());
      fm.getCurrent().setDirectory("temp1", temp1);
      String actualOutput = search.run(fm, inputArrayString);
      String expectedOutput = "";
      assertEquals(actualOutput, expectedOutput);
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), e.getMessage());
    }
  }


  /**
   * Test with no file.
   *
   * @return none
   */
  @Test
  public void NoFile() {
    try {
      String[] inputArrayString = {"search"};
      String actualOutput = search.run(fm, inputArrayString);
      String expectedOutput = "Error, nonvalid input with args";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "Error, nonvalid input with args");
    }
  }
}
