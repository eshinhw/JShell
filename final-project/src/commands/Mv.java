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

import java.util.Map;
// import org.graalvm.compiler.debug.DiagnosticsOutputDirectory;
import data.Directory;
import data.File;
import data.FileManager;
import errorHandler.NonvalidArgumentException;

/**
 * The Class Mv.
 *
 * @author Junheng Wang, Hyun Woo (Eddie) Shin, Ivanhoe Chou, Ethan Anada
 */
public class Mv implements Command {

  /**
   * Run.
   *
   * @param fm the fm
   * @param args the args
   * @return the string
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  @Override
  public String run(FileManager fm, String[] args) throws NonvalidArgumentException {
    if (args.length != 3) {
      throw new NonvalidArgumentException("error with arguments, only 3 argument expected");
    }
    Directory currentdir = fm.getCurrent();
    // check if OLDPATH is a valid dir
    if (fm.checkPathToDir(currentdir, args[1])) {
      Directory oldPathDir = fm.pathToCurrent(currentdir, args[1]);
      if (fm.checkPathToFile(args[2], currentdir)) {
        throw new NonvalidArgumentException("error, can't move dir to file");
      }
      // check if NEWPATH is a dir, if not create a dir
      if (!fm.checkPathToDir(currentdir, args[2])) {
        Mkdir mkdir = new Mkdir();
        String[] newDir = {"mkdir", args[2]};
        mkdir.run(fm, newDir);
      }
      Directory newPathDir = fm.pathToCurrent(currentdir, args[2]);
      if (ifOldPathParentOfNew(fm, oldPathDir, newPathDir)) {
        throw new NonvalidArgumentException("old path is parent of new path please try again");
      }
      String[] oldPath = {"rm", args[1]};
      moveDir(fm, oldPath, oldPathDir, newPathDir);
      return "";
      // check if OLDPath is a valid file
    } else if (fm.checkPathToFile(args[1], currentdir)) {
      File oldFile = pathToFile(fm, args[1], currentdir);
      Directory oldPathParentDir = pathToParentDir(fm, currentdir, args[1]);
      // check if NEWpath is a valid dir, if not create new file and move oldFile to Newpath
      if (!fm.checkPathToDir(currentdir, args[2])) {
        Directory newPathParentDir = pathToParentDir(fm, currentdir, args[2]);
        if (!(args[2].chars().filter(num -> num == '/').count() == 0)) {
          String newFileName = args[2].substring(args[2].lastIndexOf("/") + 1, args[2].length());
          moveFile(fm, oldFile, newFileName, oldPathParentDir, newPathParentDir);
          return "";
        }
        String newFileName = args[2];
        moveFile(fm, oldFile, newFileName, oldPathParentDir, newPathParentDir);
        return "";
      }
      Directory newPathDir = fm.pathToCurrent(currentdir, args[2]);
      moveFile(fm, oldFile, oldFile.getFileName(), oldPathParentDir, newPathDir);
      return "";
    }

    throw new NonvalidArgumentException("argument does point to valid Directory or File");
  }



  /**
   * Move file.
   *
   * @param fm the fm
   * @param file the file
   * @param fileName the file name
   * @param oldPathParentDir the old path parent dir
   * @param newPathDir the new path dir
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private void moveFile(FileManager fm, File file, String fileName, Directory oldPathParentDir,
      Directory newPathDir) throws NonvalidArgumentException {
    File newFile = new File(fileName, newPathDir);
    newFile.setContents(file.getFileContents());
    newPathDir.setFile(fileName, newFile);
    oldPathParentDir.removeFile(file.getFileName());
  }

  /**
   * Path to file.
   *
   * @param fm the fm
   * @param path the path
   * @param currentDir the current dir
   * @return the file
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private File pathToFile(FileManager fm, String path, Directory currentDir)
      throws NonvalidArgumentException {
    if (path.chars().filter(num -> num == '/').count() == 0) {
      String fileName = path;
      File file = currentDir.getFile(fileName);
      return file;
    }
    Directory oldPathParentDir =
        fm.pathToCurrent(currentDir, path.substring(0, path.lastIndexOf("/")));
    String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
    File file = oldPathParentDir.getFile(fileName);
    return file;
  }

  /**
   * Path to parent dir.
   *
   * @param fm the fm
   * @param currentDir the current dir
   * @param path the path
   * @return the directory
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private Directory pathToParentDir(FileManager fm, Directory currentDir, String path)
      throws NonvalidArgumentException {
    if (path.chars().filter(num -> num == '/').count() == 0) {
      return currentDir;
    }
    Directory pathParentDir =
        fm.pathToCurrent(currentDir, path.substring(0, path.lastIndexOf("/")));
    return pathParentDir;
  }

  /**
   * Move dir.
   *
   * @param fm the fm
   * @param oldPath the old path
   * @param oldPathDir the old path dir
   * @param newPathDir the new path dir
   * @throws NonvalidArgumentException the nonvalid argument exception
   */
  private void moveDir(FileManager fm, String[] oldPath, Directory oldPathDir, Directory newPathDir)
      throws NonvalidArgumentException {
    String oldPathDirName = oldPathDir.getDirName();
    Directory tempDirectory = new Directory(oldPathDirName, newPathDir);
    newPathDir.setDirectory(oldPathDirName, tempDirectory);
    Directory movedDir = newPathDir.getDirectory(oldPathDirName);
    try {
      for (Map.Entry<String, Directory> d : oldPathDir.getDirectoryHashMap().entrySet()) {
        movedDir.setDirectory(d.getKey(), d.getValue());
      }
    } catch (Exception e) {
      ;
    }
    try {
      for (Map.Entry<String, File> f : oldPathDir.getFileHashMap().entrySet()) {
        movedDir.setFile(f.getKey(), f.getValue());
      }
    } catch (Exception e) {
      ;
    }

    Rm rm = new Rm();
    rm.run(fm, oldPath);
  }

  /**
   * If old path parent of new.
   *
   * @param fm the fm
   * @param oldDir the old dir
   * @param newDir the new dir
   * @return true, if successful
   */
  private boolean ifOldPathParentOfNew(FileManager fm, Directory oldDir, Directory newDir) {
    String fullPathNew = "/" + fm.getCurrentFullPath(newDir);
    String fullPathOld = "/" + fm.getCurrentFullPath(oldDir);
    if (fullPathNew.contains(fullPathOld)) {
      return true;
    }
    return false;
  }
}
