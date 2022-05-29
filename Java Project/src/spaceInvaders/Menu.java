package spaceInvaders;

import java.util.HashMap;

public class Menu {
  private SpaceInvader spaceInvader;
  private HighScoreController highScores;
  private final HashMap<Integer, String> KEYS = new HashMap<>();

  // Strings
  private final String HEAD = "=== SPACE INVADERS ===";
  private final String BLANK = "=                    =";
  private final String INFO = "press 'd' to %s ";
  private final String END = "======================";
  private final String[][] MAIN_MENU_ITEMS = {
      {
          "=      > PLAY        =",
          "=        PLAY        ="
      },
      {
          "=   > HIGH SCORE     =",
          "=     HIGH SCORE     ="
      },
      {
          "=      > EXIT        =",
          "=        EXIT        ="
      }
  };

  /** Menu item currently selected */
  private int itemSelected = 0;
  /**
   * <ol>
   * <li>Main Menu
   * <li>High Scores
   * </ol>
   */
  private int stateMachine = 1;

  Menu(SpaceInvader spaceInvader, HighScoreController highScores) {
    KEYS.put(0, "play");
    KEYS.put(1, "exit");
    this.spaceInvader = spaceInvader;
    this.highScores = highScores;
  }

  /**
   * Updates the menu on the screen.
   */
  public void update(char key) {
    String values = null;
    manageInput(key);

    switch (stateMachine) {
      case 1:
        values = mainMenu();
        break;
      case 2:
        values = scores();
        break;
      default:
        break;
    }

    System.out.println(values);
  }

  /**
   * @return the current state of the Main Menu formatted as a String ready to be
   *         printed.
   */
  public String mainMenu() {
    StringBuilder sb = new StringBuilder();
    sb.append(HEAD + "\n");
    sb.append(BLANK + "\n");

    for (int i = 0; i < MAIN_MENU_ITEMS.length; i++) {
      int aux = 1;
      if (i == itemSelected)
        aux = 0;
      sb.append(MAIN_MENU_ITEMS[i][aux] + "\n");
    }

    sb.append(BLANK + "\n");
    sb.append(END + "\n");
    sb.append(String.format(INFO, "select") + "\n");

    return sb.toString();
  }

  /**
   * @return the high scores table formatted as a String ready to be
   *         printed.
   */
  public String scores() {
    String[][] values = highScores.getScores();
    StringBuilder sb = new StringBuilder();

    sb.append(HEAD + "\n");
    sb.append(BLANK + "\n");

    // Formatting the scores to comply with the menu aesthetics.
    // It works.
    for (String[] strings : values) {
      String name = String.format("%-" + HighScoreController.SCORES_NAME_SIZE + "s", strings[0]);
      String score = String.format("%-" + HighScoreController.SCORES_NAME_SIZE + "s", strings[1]);
      int separator = BLANK.length() - name.length() - score.length();
      sb.append(String.format("=%" + separator + "s %-" + (separator - 1) + "s=", name, score));
      sb.append("\n");
    }

    sb.append(BLANK + "\n");
    sb.append(END + "\n");
    sb.append(String.format(INFO, "exit") + "\n");

    return sb.toString();
  }

  /**
   * Selects the next item in the menu, if going out of bounds, it loops back to
   * the first item.
   */
  protected void nextItem() {
    itemSelected++;
    itemSelected = (itemSelected > MAIN_MENU_ITEMS.length - 1) ? 0 : itemSelected;
  }

  /**
   * Selects the previous item in the menu, if going out of bounds, it loops back
   * to the last item.
   */
  protected void previousItem() {
    itemSelected--;
    itemSelected = (itemSelected < 0) ? MAIN_MENU_ITEMS.length : itemSelected;
  }

  public String selectItem() {
    return KEYS.get(itemSelected);
  }

  private void manageInput(char key) {
    switch (Character.toLowerCase(key)) {
      case 'w':
        previousItem();
        break;
      case 's':
        nextItem();
        break;
      case 'd':
        takeAction();
        break;
      default:
        break;
    }
  }

  private void takeAction() {
    switch (stateMachine) {
      case 1:
        takeActionMainMenu();
        break;
      case 2:
        takeActionScores();
        break;
      default:
        break;
    }

  }

  private void takeActionMainMenu() {
    switch (itemSelected) {
      case 0:
        spaceInvader.startGame();
        break;
      case 1:
        stateMachine = 2;
        break;
      case 2:
        spaceInvader.exitApp();
        break;
      default:
        break;
    }
  }

  private void takeActionScores() {
    stateMachine = 1;
  }

}
