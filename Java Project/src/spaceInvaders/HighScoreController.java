package spaceInvaders;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class HighScoreController {
  // Database
  private final String DB_NAME = "space-invaders";
  private final String DB_TABLE_NAME = "high-scores";
  private final String DB_USER = "root";
  private final String DB_PASSWORD = "";
  private boolean dbConnection;

  // File
  private final String FILE_NAME = "high-scores";
  private final String FILE_PATH = "Java Project\\res\\";
  private final String FILE = FILE_PATH + FILE_NAME;

  // High Scores
  private final int SCORES_SIZE = 5;
  private String[][] scores = new String[SCORES_SIZE][2];

  public HighScoreController() {
    createFile();
  }

  /**
   * Initializes the scores array.
   * First it'll try to get them from the database, if that's not possible, it'll
   * try to retrieve them from a 'high-score.txt' file.
   * As a last resort, it'll generate a set of dummy values.
   */
  public void initScores() {
    String[][] values;

    if (false)
      ;
    else if (!fileIsEmpty())
      values = readFromFile();
    else
      values = generateMockValues();

    setScores(values);
  }

  public void addScore() {

  }

  public void setScores(String[][] values) {
    scores = values.clone();
  }

  public String[][] getScores() {
    return scores;
  }

  public String[][] readFromFile() {
    String[][] values = new String[SCORES_SIZE][2];
    Scanner in = null;
    String result = "";
    int counter = 0;

    try {
      in = new Scanner(new FileReader(FILE));
      in.useLocale(Locale.ROOT);

      // lee el fichero palabra a palabra
      while (in.hasNext() && counter < SCORES_SIZE) {
        result = in.nextLine();
        values[counter][0] = result.split(" ")[0];
        values[counter][1] = result.split(" ")[1];
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      if (in != null) {
        in.close();
      }
    }

    return values;
  }

  public void saveToFile() {
    PrintWriter out = null;

    try {
      out = new PrintWriter(new FileWriter(FILE));
      for (String[] score : scores) {
        out.println(score.toString());
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      if (out != null)
        out.close();
    }
  }

  public boolean fileIsEmpty() {
    File file = new File(FILE);

    return file.length() == 0 ? true : false;
  }

  private void createFile() {
    try {
      File file = new File(FILE);
      if (file.createNewFile())
        System.out.println("File created.");
      else
        System.out.println("File already exists.");

    } catch (Exception e) {
      System.out.println("An error occurred.");
    }
  }

  /**
   * Generates a mock scoreboard filled with:
   * ["PC", "0"]
   */
  private String[][] generateMockValues() {
    String[][] values = new String[SCORES_SIZE][2];

    for (int i = 0; i < SCORES_SIZE; i++) {
      values[i][0] = "PC";
      values[i][1] = "0";
    }

    return values;
  }

  public void printScores() {
    for (int i = 0; i < scores.length; i++) {
      System.out.println(Arrays.toString(scores[i]));
    }
  }
}
