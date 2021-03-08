package a0;

// **********************************************************
// Assignment0:
// UTOR user_name: shinhy22
// UT Student #: 997743615
// Author: Hyun Woo (Eddie) Shin
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagarism section in the course info
// sheet of CSC B07 and understand the consequences. Note that
// we will be running all your quizzes and assignments for plagarism
// check starting sometime in November of 2020. If you do not complete this honor
// code, we will give you no credit and award you 0% for this component.


/*
 * 
 */
public class Rearranging {
  /*
   * TODO: You are asked to complete the method rearranging. 
   * This method takes in as input an array 
   * of ints and returns back nothing (void). 
   * You cannot change the function signature of this method.
   */
  public static void rearranging(int[] items) {
    int j = 0;

    for (int i = 0; i < items.length; i++) {

      if (items[i] < 0) {
        swap(i, j, items);
        j++;
      }
    }
  }

  /*
   * TODO: You are asked to complete the method swap. 
   * This method takes in as input two ints and an array of ints. 
   * The int i and int j are the index i and index j in the array items. 
   * You are asked to swap the value at the index i in items 
   * with the value at the index j. 
   * You cannot change the function signature of this method.
   */
  private static void swap(int i, int j, int[] items) {
    int temp;
    temp = items[i];
    items[i] = items[j];
    items[j] = temp;
  }

  public static void main(String[] args) {
    /*
     * You can modify the main function in any way you like. 
     * We will not mark your main function.
     */
    int[] items = {7, -3, 0, 0, 8, -2};
    /*
     * printing the values in the items before calling the method rearranging
     */
    for (int item : items) {
      System.out.println(item);
    }

    // calling the rearranging method
    Rearranging.rearranging(items);
    /*
     * printing the values in the items after calling the method rearranging
     */
    for (int item : items) {
      System.out.println(item);
    }
  }
}
