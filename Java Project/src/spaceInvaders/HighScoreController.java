package spaceInvaders;

// import dataManagement.DatabaseManager;
import dataManagement.FilesManager;

public class HighScoreController {
  // Database
  // private final String DB_NAME = "space-invaders";
  // private final String DB_TABLE = "high-scores";
  // private final String DB_USER = "root";
  // private final String DB_PASSWORD = "";
  // private boolean dbConnection;
  // private DatabaseManager dbManager;

  // File
  private final String FILE_NAME = "high-scores";
  private final String FILE_PATH = "Java Project\\res\\" + FILE_NAME;
  private FilesManager fileManager;

  // High Scores
  private final static int SCORES_NUMBER = 5;
  public final static int SCORES_NAME_SIZE = 6;
  private static String[][] scores = null;

  public HighScoreController() {
    fileManager = new FilesManager();
    fileManager.createFile(FILE_PATH);

    if (scores == null)
      setScores(retrieveScores());
  }

  /**
   * Attempts to retrieve a saved scores array, should that fail, it will return a
   * dummy array.
   * 
   * <ul>
   * <li>First it'll try to get the scores from the database.
   * <li>If that's not possible, it'll * try to retrieve them from the
   * 'high-score.txt' file.
   * <li>As a last resort, it'll generate a set of dummy values.
   * </ul>
   * 
   * @return a score array
   */
  private String[][] retrieveScores() {
    String[][] values;

    if (false)
      values = retrieveScoreFromDataBase();
    else if (!fileManager.fileIsEmpty(FILE_PATH))
      values = retrieveScoreFromFile();
    else
      values = generateMockValues();

    return values;
  }

  /**
   * @return The scores array.
   */
  public String[][] getScores() {
    return scores;
  }

  /**
   * Sets the scores array. It'll also update the database and the file.
   * <p>
   * If the values array is not composed of valid scores, it'll initialize the
   * scores array with dummy.
   * 
   * @param values The values to set.
   */
  public void setScores(String[][] values) {
    if (validateScores(values))
      HighScoreController.scores = values.clone();
    else
      HighScoreController.scores = generateMockValues().clone();

    saveScores(HighScoreController.scores);
  }

  /** Saves the scores array to the database and the file. */
  public void saveScores(String[][] values) {
    // TODO: Save the scores to the database
    fileManager.writeFile(FILE_PATH, values);
  }

  /**
   * @return The scores array from the {@link #FILE_PATH}.
   */
  private String[][] retrieveScoreFromFile() {
    String[][] result;

    try {
      result = (String[][]) fileManager.readFile(FILE_PATH);
    } catch (Exception e) {
      result = generateMockValues();
    }

    return result;
  }

  // TODO: Get the values from the database
  private String[][] retrieveScoreFromDataBase() {
    String[][] result = null;
    return result;
  }

  /**
   * Validates the scores array, looking for invalid values and inconsistencies.
   * 
   * @param values The scores array to validate.
   * @return True if the scores array is valid, false otherwise.
   */
  private boolean validateScores(String[][] values) {

    // Check if the array is of the correct size
    if (values.length != SCORES_NUMBER) {
      return false;
    }

    // Score value must be a number
    for (int i = 0; i < values.length; i++) {
      if (values[i][1].matches("[a-zA-Z]"))
        return false;
    }

    // Check if the scores are sorted
    for (int i = 0; i < values.length - 1; i++) {
      if (Integer.parseInt(values[i][1]) < Integer.parseInt(values[i + 1][1]))
        return false;
    }

    // Check if there are null values
    for (int i = 0; i < values.length; i++) {
      if (values[i][0] == null || values[i][1] == null)
        return false;
    }

    return true;
  }

  /**
   * Prints the contents of the high scores matrix.
   */
  public static void printScores() {
    StringBuilder sb = new StringBuilder();
    String highScore = " HIGH SCORES ";
    int spacing = SCORES_NAME_SIZE - highScore.length() / 2 + 2;

    // Header
    sb.append("-".repeat(spacing) + highScore + "-".repeat(spacing) + "\n");

    // Loop through the scores array.
    for (String[] score : scores) {
      sb.append(String.format("|%-" + SCORES_NAME_SIZE + "s | ", score[0]));
      sb.append(String.format("%-" + SCORES_NAME_SIZE + "s|", score[1]));
      sb.append("\n");
    }

    // Print
    System.out.println(sb.toString());
  }

  /**
   * Generates a mock scoreboard filled with:
   * ["PC", "0"]
   */
  private String[][] generateMockValues() {
    String[][] values = new String[SCORES_NUMBER][2];

    for (int i = 0; i < SCORES_NUMBER; i++) {
      values[i][0] = "PC";
      values[i][1] = "0";
    }

    return values;
  }

  private void resetScores() {
    scores = generateMockValues();
  }

  public void addScore(String name, int score) {
    if (isNewHighScore(score)) {
      addNewHighScore(name, score);
    }
  }

  /**
   * Checks if a new high score had been achieved.
   * 
   * @param score score to check
   * @return Returns true if its a new high score, false if not
   */
  private boolean isNewHighScore(int score) {
    for (int i = 0; i < scores.length; i++) {
      if (score >= Integer.valueOf(scores[i][1]))
        return true;
    }
    return false;
  }

  /**
   * Adds a new record to the array, deleting the last record.
   * 
   * @param name  Name to put on the wall of fame.
   * @param score Number of moves taken to beat the game.
   * @return Array with the new high score included
   */
  private void addNewHighScore(String name, int score) {
    String[][] values = getScores();
    int position = 0;

    // Finds the position the new score would hold in the ranking
    while (Integer.valueOf(values[position][1]) > score) {
      position++;
    }

    // Shift all items one position from the bottom up,
    // ending in the selected position
    for (int i = values.length - 1; i > position; i--) {
      values[i][0] = values[i - 1][0];
      values[i][1] = values[i - 1][1];
    }

    // Adds the new high score
    values[position][0] = name;
    values[position][1] = Integer.toString(score);

  }

}
