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
package commands;

import java.util.HashMap;
import data.*;
import errorHandler.*;

/**
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
/**
 * Print documentation for CMD (s)
 */
public class Man implements Command {

  /** The cmd manual. */
  private HashMap<String, String> cmdManual;

  /**
   * Instantiates a new man.
   */
  public Man() {
    cmdManual = initializeCmdManual();
  }

  /**
   * Run.
   *
   * @param fm the fileManager where the command is executed
   * @param args the args
   * @return A string of the documentation of desired command
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private String manHelper(FileManager fm, String[] args) throws NonvalidArgumentException {
    if (args.length != 2)
      throw new NonvalidArgumentException("error, requires only one command argument");

    String outputString = "";
    for (int i = 1; i < args.length; i++) {
      if (cmdManual.containsKey(args[i])) {
        outputString += cmdManual.get(args[i]);
      } else {
        throw new NonvalidArgumentException("error, unable to recognize command");
      }
      if (args[i].contains(">")) {
        fm.redirection(fm, args, outputString);
        return "";
      }
    }

    return outputString;
  }

  /**
   * Run.
   *
   * @param fm the fm
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {

    if (fm.redirectionChecker(args) == 1) {
      String outString = fm.formatRedirect(args);
      outString = outString.trim();
      String[] holderString = outString.split("\\s+");
      String output = manHelper(fm, holderString);
      fm.redirection(fm, args, output);
      return "";

    }
    return manHelper(fm, args);

  }

  /**
   * Command description formatting.
   *
   * @param cmd The desired command
   * @param doc The documentation for the input command
   * @param synopsis The synopsis for the input command
   * @return String of the name, synopsis and documentation for the command
   */
  private String commandDescriptionFormatting(String cmd, String doc, String synopsis) {
    String output = "";

    output += "\nNAME\n\t\t" + cmd + "\n";
    output += "SYNOPSIS\n\t\t" + synopsis + "\n";
    output += "DESCRIPTION\n\t\t" + doc + "\n\n";

    return output;
  }


  /**
   * Initialize cmd manual.
   *
   * @return the hash map
   */
  private HashMap<String, String> initializeCmdManual() {

    HashMap<String, String> cmdMan = new HashMap<String, String>();

    cmdMan.put("exit", commandDescriptionFormatting("exit", "Quit the program", "exit"));
    cmdMan.put("mkdir", commandDescriptionFormatting("mkdir",
        "Create directories, if they do not already exist.", "mkdir DIR ..."));
    cmdMan.put("cd",
        commandDescriptionFormatting("cd",
            "Change the shell working directory.\n\t\t" + "Change the current directory to DIR.",
            "cd DIR"));
    cmdMan.put("ls",
        commandDescriptionFormatting("ls",
            "List directory contents.\n\t\t-R, List all subdirectories recursively",
            "ls [-R] [PATH ...]"));

    cmdMan.put("pwd",
        commandDescriptionFormatting("pwd", "Print name of current working directory.", "pwd"));

    cmdMan.put("pushd",
        commandDescriptionFormatting("pushd",
            "Saves the current working directory by pushing onto directory stack.\n\t\t"
                + "Then changes the current working directory to DIR.",
            "pushd DIR"));

    cmdMan.put("popd", commandDescriptionFormatting("popd",
        "Remove the top entry from the directory stack, and cd into it.", "popd"));

    cmdMan.put("history",
        commandDescriptionFormatting("history",
            "Print out recent commands.\n\t\t"
                + "Truncates the output by specifying a number after the command.",
            "history [number]"));

    cmdMan.put("cat",
        commandDescriptionFormatting("cat",
            "Display the contents of FILE1 and other files concatenated in the shell.",
            "cat FILE1 ..."));

    cmdMan.put("echo",
        commandDescriptionFormatting("echo",
            "If OUTFILE is not provided, print STRING on the shell.\n\t\t"
                + "Otherwise, put STRING into file OUTFILE.",
            "echo STRING [> OUTFILE]\n\t\techo STRING >> OUTFILE"));

    cmdMan.put("man",
        commandDescriptionFormatting("man", "Print documentation for CMD.", "man CMD"));

    cmdMan.put("rm",
        commandDescriptionFormatting("rm", "Remove DIR from the file system.", "rm DIR"));

    cmdMan.put("mv",
        commandDescriptionFormatting("mv", "Move item OLDPATH to NEWPATH.", "mv OLDPATH NEWPATH"));

    cmdMan.put("cp",
        commandDescriptionFormatting("cp", "Copy item OLDPATH to NEWPATH.", "cp OLDPATH NEWPATH"));

    cmdMan.put("curl", commandDescriptionFormatting("curl",
        "Retrieve a file at URL and add it to the current working directory.", "curl URL"));

    cmdMan.put("saveJShell", commandDescriptionFormatting("saveJShell",
        "Save the current session of JShell to FileName.", "saveJShell FileName"));

    cmdMan.put("loadJShell",
        commandDescriptionFormatting("loadJShell",
            "Load the contents of the FileName"
                + " and reinitialize everything that was saved previously into the FileName.",
            "loadJShell FileName"));

    cmdMan.put("search",
        commandDescriptionFormatting("search", "Search files or directories in the file system.",
            "search path ... -type [f|d] -name expression"));

    cmdMan.put("tree", commandDescriptionFormatting("tree",
        "Print the entire file system as a tree structure", "tree"));
    return cmdMan;
  }

}

