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
import commands.Curl;
import errorHandler.NonvalidArgumentException;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */

public class CurlTest {

  Curl curl = new commands.Curl();
  private MockFileManager MockFM;
  private String txturl;
  private String htmlurl;
  private String txtname;
  private String htmlname;
  private String badurl;


  /**
   * CurlTest command setup
   * 
   * @param none
   * @return none
   */

  @Before
  public void setUp() {
    MockFM = new MockFileManager();
    txturl = "http://www.cs.cmu.edu/~spok/grimmtmp/073.txt";
    txtname = "073txt";
    htmlurl = "http://www.cs.utoronto.ca/~trebla/CSCB09-2020-Summer/l03/Lab03.html";
    badurl = "http://www.ub.edu/gilcub/SIMPLE/simple.html";

  }

  /**
   * Create txt file with contents from working url
   * 
   * @param none
   * @return none
   */

  @Test
  public void txtfile() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"curl", "txturl"};
      String actualOutput = curl.run(MockFM, inputArrayString);
      String expectedOutput = "\n";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "invalid input, URL has problems");
    }
  }



  @Test
  /**
   * Create html file with contents from working url
   * 
   * @param none
   * @return none
   */
  public void htmlurl() {
    try {
      String[] inputArrayString = {"curl", "htmlurl"};
      String actualOutput = curl.run(MockFM, inputArrayString);
      String expectedOutput = "\n";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "invalid input, URL has problems");
    }
  }


  @Test
  /**
   * Curl with an invalid url
   * 
   * @param none
   * @return none
   */
  public void badurl() {
    try {
      String[] inputArrayString = {"curl", "badurl"};
      String actualOutput = curl.run(MockFM, inputArrayString);
      String expectedOutput = "invalid input, URL has problems";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "invalid input, URL has problems");
    }
  }



  @Test
  public void noinput() throws NonvalidArgumentException {
    try {
      String[] inputArrayString = {"curl"};
      String actualOutput = curl.run(MockFM, inputArrayString);
      String expectedOutput = "error with arguments, only 2 arguments expected";
      assertEquals(actualOutput, expectedOutput);
      fail();
    } catch (NonvalidArgumentException e) {
      assertEquals(e.getMessage(), "error with arguments, only 2 arguments expected");
    }
  }
}
