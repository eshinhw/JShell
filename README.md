# Mock File System in Java

<p align="center">
  <img width="800" height="400" src="https://user-images.githubusercontent.com/41933169/116823837-cf646d80-ab54-11eb-89d9-392581c0c79d.png">
</p>

## List of Available Commands

#### 1. rm DIR

Remove the DIR from the file system.

#### 2. exit

Quit the program.

#### 3. mkdir DIR ...

Create directories, each of which may be relative to the current directory or maybe a full path.

#### 4. cd DIR

Change directory to DIR, which may be relative to the current directory or maybe a full path.

#### 5. ls [-R] [PATH ...]

If -R is present, recursively list all subdirectories.
If no paths are given, print the contents of the current directory, with a new line following each of the content (file or directory).

#### 6. pwd

Print the current working directory (including the whole path).

#### 7. mv OLDPATH NEWPATH

Move item OLDPATH to NEWPATH.

#### 8. cp OLDPATH NEWPATH

Copy the contents from OLDPATH to NEWPATH. If OLDPATH is a directory, recursively copy the contents to NEWPATH.

#### 9.cat FILE ...

Display the contents of FILE on the console. For any file that contains an invalid path, display an appropriate error for that path only.

#### 10. curl URL

Retrieve the file at that URL and add it to the current working directory.

#### 11. echo String

Print String.

#### 12. man CMD

Print documentation for CMD. The man command now only takes one argument.

#### 13. pushd DIR

Saves the current working directory by pushing onto directory stack and then changes the new current working directory to DIR.

#### 14. popd

Remove the top entry from the directory stack, and cd into it.

#### 15. history [number]

Print out recent commands, one command per line.

#### 16. saveJShell FILENAME

Save the current session of the JShell as an external file before the user closes it down.

#### 17. loadJShell FILENAME

Load the previous session info from reading an external file.

#### 18. search

Search files and directories in the file system.

#### 19. tree

Display the entire file system as a tree.
