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
package driver;

import java.util.HashMap;
import java.util.Scanner;
import data.JFileManager;
import errorHandler.NonvalidArgumentException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * Our JShell, where our user is able enter commands and receive the appropriate output, much like a
 * real shell would
 */
public class JShell {

  /** The fileManager. */
  private static JFileManager fm = JFileManager.createFileManager();

  /** The command hash map. */
  private static HashMap<String, String> commandHashMap = initializeCommandHashMap();

  /**
   * Initialize command hash map.
   *
   * @return the hash map
   */
  private static HashMap<String, String> initializeCommandHashMap() {
    HashMap<String, String> commandHashMap = new HashMap<String, String>();
    commandHashMap.put("exit", "commands.Exit");
    commandHashMap.put("mkdir", "commands.Mkdir");
    commandHashMap.put("cd", "commands.Cd");
    commandHashMap.put("ls", "commands.Ls");
    commandHashMap.put("pwd", "commands.Pwd");
    commandHashMap.put("pushd", "commands.Pushd");
    commandHashMap.put("popd", "commands.Popd");
    commandHashMap.put("history", "commands.History");
    commandHashMap.put("cat", "commands.Cat");
    commandHashMap.put("echo", "commands.Echo");
    commandHashMap.put("man", "commands.Man");
    commandHashMap.put("rm", "commands.Rm");
    commandHashMap.put("mv", "commands.Mv");
    commandHashMap.put("cp", "commands.Cp");
    commandHashMap.put("curl", "commands.Curl");
    commandHashMap.put("saveJShell", "commands.SaveJShell");
    commandHashMap.put("loadJShell", "commands.LoadJShell");
    commandHashMap.put("search", "commands.Search");
    commandHashMap.put("tree", "commands.Tree");
    return commandHashMap;
  }

  /**
   * 
   * Adds an user input to inputHistory for history command and Parses the user input for input
   * validity inspection
   * 
   * @param userInput
   * @return userInput parsed into arguments
   */
  private static String[] preprocessingInput(String userInput) {
    userInput = userInput.trim();
    String updatedInputString = "";
    fm.addInputHistory(userInput);

    if (userInput.contains(">")) {
      String[] arrowParser = userInput.split(">", 2);
      // we have one >
      if (arrowParser.length == 2 && (!arrowParser[1].contains(">"))) {
        arrowParser[1] = " > " + arrowParser[1];
      }
      // We have >>
      else if (arrowParser.length == 2 && (arrowParser[1].contains(">"))) {
        arrowParser[1] = " >" + arrowParser[1];
      }
      for (String x : arrowParser) {
        updatedInputString += x;
      }
      userInput = updatedInputString;
    }


    String[] argInputs = userInput.split("\\s+");
    return argInputs;
  }

  /**
   * Creates an instance of valid command class and executes the necessary tasks from the instance.
   * Corresponding output from each command execution should be printed out on the shell.
   * 
   * @param comName
   * @param argInputs
   */
  private static void commandOutput(String comName, String[] argInputs) {
    try {
      commands.Command inputCommand =
          (commands.Command) Class.forName(comName).getDeclaredConstructor().newInstance();
      try {
        String outputResult = inputCommand.run(fm, argInputs);
        if (outputResult != "")
          System.out.print(outputResult);
        System.out.print(">> ");
      } catch (NonvalidArgumentException e) {
        System.out.println(e.getMessage());
        System.out.print(">> ");
      }
    } catch (InstantiationException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (IllegalAccessException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (IllegalArgumentException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (InvocationTargetException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (NoSuchMethodException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (SecurityException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    } catch (ClassNotFoundException e) {
      System.out.println("error, something wrong with java reflection");
      System.out.print(">> ");
    }
  }

  public static void loadPreviousJShell(JFileManager new_fm) {
    JShell.fm = new_fm;
  }

  /**
   * Takes input commands from the user and tries to run the command
   *
   * @param args the command
   */
  public static void main(String[] args) {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    String userInput = "";
    System.out.println("Welcome to JShell\n");
    System.out
        .println("* Developed by: Junheng Wang, Eddie Shin, Ethan Ananda and Ivanhoe Chou\n\n");
    System.out.print(">> ");
    while (true) {
      userInput = scanner.nextLine();
      String[] argInputs = preprocessingInput(userInput);
      if (commandHashMap.containsKey(argInputs[0])) {
        try {
          String name = commandHashMap.get(argInputs[0]);
          commandOutput(name, argInputs);
        } catch (Exception e) {
          System.out.println("error, something wrong with command output");
          System.out.print(">> ");
        }
      } else {
        System.out.println("error, invalid input");
        System.out.print(">> ");
      }
    }
  }
}
