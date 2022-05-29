import spaceInvaders.SpaceInvader;

public class App {
  public static void main(String[] args) throws Exception {
    SpaceInvader sp = new SpaceInvader();
    sp.start();

    //String[][] testScores = { { "1", "2" }, { "3", "4" }, { "5", "6" }, { "7", "8" }, { "9", "10" }, { "11", "12" }, { "13", "14" } };
    //String[][] testScores2 = { { "1", "10" }, { "3", "9" }, { "5", "7" }, { "7", "6" }, { "9", "1" } };

    // HighScoreController hs = new HighScoreController();

    // hs.setScores(testScores2);
    // hs.printScores();
    // System.out.println(hs.isNewHighScore(400));
    // hs.addNewHighScore("Alex", 400);
    // hs.addNewHighScore("Alex", 400);
    // hs.addNewHighScore("Alex", 400);
    // hs.printScores();
    // hs.saveScores(hs.getScores());

  }
}
