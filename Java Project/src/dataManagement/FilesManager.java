package dataManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class used to work with binary files.
 * 
 * @author Alejandro Pérez - 2022
 */
public class FilesManager {

  /**
   * Attempts to create a file.
   * 
   * @param path The file's path.
   * @return True if the file was created or already existed, false otherwise.
   */
  public boolean createFile(String path) {
    boolean fileCreated = true;
    System.out.println("\nCreating file...");

    try {

      Files.createFile(Path.of(path));
      System.out.println("File created.");

    } catch (FileAlreadyExistsException e) {

      System.out.println("File already exists.");

    } catch (Exception e) {

      System.out.println("An error occurred.");
      fileCreated = false;

    }

    return fileCreated;
  }

  /**
   * Reads a file and returns the content as an object. It's up to the caller to
   * cast the object to the desired type.
   * 
   * @param path The path to the file.
   * @return Contents of the file as an object.
   */
  public Object readFile(String path) {
    Object result = null;
    ObjectInputStream in = null;

    try {

      in = new ObjectInputStream(new FileInputStream(path));
      result = in.readObject();

    } catch (Exception e) {
      
      System.out.println("An error occurred whilst reading the file.");

    } finally {

      try {
        in.close();
      } catch (IOException e) {
        System.out.println("An error occurred whilst closing the input stream.");
      }

    }

    return result;
  }

  /**
   * Writes the objects to the specified file.
   * 
   * @param path The path of the file.
   * @param o    The object to write.
   */
  public void writeFile(String path, Object o) {
    ObjectOutputStream out = null;
    System.out.println("\nWriting file...");

    try {

      out = new ObjectOutputStream(new FileOutputStream(path));
      out.writeObject(o);
      System.out.println("File written!");

    } catch (Exception e) {

      System.out.println("An error occurred.");

    } finally {

      try {
        out.close();
      } catch (IOException e) {
        System.out.println("An error occurred whilst closing the output stream.");
      }

    }
  }

  /**
   * Checks if a file exists.
   * 
   * @param path The file's path.
   * @return True if the file exists, false otherwise.
   */
  public boolean fileExists(String path) {
    boolean exists = false;
    System.out.println("\nChecking if file exists...");

    try {
      exists = Files.exists(Path.of(path));
    } catch (Exception e) {
      System.out.println("An error occurred.");
    }

    return exists;
  }

  /**
   * Checks if a file is empty.
   * 
   * @param path The file's path.
   * @return True if the file is empty, false otherwise.
   */
  public boolean fileIsEmpty(String path) {
    boolean isEmpty = false;
    System.out.println("\nChecking if file is empty...");

    try {
      isEmpty = Files.size(Path.of(path)) == 0;
    } catch (Exception e) {
      System.out.println("An error occurred.");
    }

    return isEmpty;
  }

}