import spaceInvaders.*;

public class App {
  public static void main(String[] args) throws Exception {
    // SpaceInvader sp = new SpaceInvader();
    // sp.start();
    HighScoreController highScores = new HighScoreController();
    highScores.initScores();
    highScores.printScores();
    highScores.saveToFile();
    highScores.printScores();

    System.out.println(highScores.fileIsEmpty());
  }
}
