package a0;

import java.lang.Math;
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

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /*
   * TODO:COMPLETE THIS 
   * I.E. APPROPRIATELY CREATE THE userMovieMatrix AND userUserMatrix WITH
   * CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size users*movies which will store
   * integer movie ratings and one of size users*users which will store float similarity scores
   * between pairs of users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input parameters it takes in
   * a rowNumber, columnNumber and a rating value. The rating value is then inserted in the
   * UserMovieMatrix at the specified rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber, int ratingValue) {
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT CHANGE THE FUNCTION
   * NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This similarity value is
   * represented with with a float value between 0 and 1, where 1 is perfect/identical similarity.
   * Stores these values in the userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void calculateSimilarityScore(int numUsers, int numMovies) {

    for (int i = 0; i < numUsers; i++) {

      for (int j = i + 1; j < numUsers; j++) {
        double innerSum = 0;
        for (int k = 0; k < numMovies; k++) {
          double diff = userMovieMatrix[i][k] - userMovieMatrix[j][k];
          innerSum = (innerSum + Math.pow(diff, 2));
          // System.out.println("inner sum: " + innerSum);
        }
        double temp = Math.sqrt(innerSum);

        userUserMatrix[i][j] = (float) (1 / (1 + temp));
        userUserMatrix[j][i] = (float) (1 / (1 + temp));

      }
    }

    for (int i = 0; i < numUsers; i++)
      userUserMatrix[i][i] = 1;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT CHANGE THE FUNCTION
   * NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and column representing
   * each/single user and the cell position (i,j) representing the similarity score between user i
   * and user j.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void printUserUserMatrix(int numUsers) {

    System.out.println();
    System.out.println();
    System.out.println("userUserMatrix is");

    for (int row = 0; row < numUsers; row++) {
      // float[] values = new float[numUsers];

      System.out.print("[");

      for (int col = 0; col < numUsers; col++) {
        if (col == numUsers - 1) {
          System.out.format("%.4f]\n", userUserMatrix[row][col]);

        } else
          System.out.format("%.4f, ", userUserMatrix[row][col]);
      }
    }

  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT CHANGE THE FUNCTION
   * NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most similar pair of users in the userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void findAndprintMostSimilarPairOfUsers(int numUsers) {

    float maxRating = 0;

    for (int i = 0; i < numUsers; i++) {
      for (int j = i + 1; j < numUsers; j++) {
        if (userUserMatrix[i][j] > maxRating) {
          maxRating = userUserMatrix[i][j];
        }
      }
    }
    System.out.println();
    System.out.println();
    System.out.println("The most similar pairs of users from above userUserMatrix are:");

    for (int i = 0; i < numUsers; i++) {
      for (int j = i + 1; j < numUsers; j++) {
        if (userUserMatrix[i][j] == maxRating) {
          System.out.format("User %d and User %d\n", i + 1, j + 1);
        }
      }
    }
    System.out.format("with similarity score of %.4f\n", maxRating);
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT CHANGE THE FUNCTION
   * NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most dissimilar pair of users in the userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostDissimilarPairOfUsers(int numUsers) {

    float minRating = 100;

    for (int i = 0; i < numUsers; i++) {
      for (int j = i + 1; j < numUsers; j++) {
        if (userUserMatrix[i][j] < minRating) {
          minRating = userUserMatrix[i][j];

        }
      }
    }
    System.out.println();
    System.out.println();
    System.out.println("The most dissimilar pairs of users from above userUserMatrix are:");

    for (int i = 0; i < numUsers; i++) {
      for (int j = i + 1; j < numUsers; j++) {
        if (userUserMatrix[i][j] == minRating) {
          System.out.format("User %d and User %d\n", i + 1, j + 1);
        }
      }
    }
    System.out.format("with similarity score of %.4f", minRating);
    System.out.println();
    System.out.println();
  }
}
