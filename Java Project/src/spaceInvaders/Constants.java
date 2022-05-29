package spaceInvaders;

// TODO: BUG on difficulty increase over 5 the enemies freeze in place

public interface Constants {

  // Board
  public final int BOARD_SIZE_X = 10;
  public final int BOARD_SIZE_Y = 10;
  public final char SPR_EMPTY = ' ';

  // Player
  public final char SPR_PLAYER = 'X';
  public final int SPD_PLAYER = 1;
  public final int PLR_SHOOT_DELAY = 2;

  // Enemy
  public final char SPR_ENEMY = 'Y';
  public final int SPD_ENEMY = 15;
  public final int SPD_ENEMY_MIN = 7;
  public final int PT_ENEMY = 5;

  // Bullet
  public final char SPR_BULLET = 'o';
  public final int SPD_BULLET = 2;

  // Game
  public final int SLEEP_TIME = 30;
  public final int MAX_DIFFICULTY = SPD_ENEMY - SPD_ENEMY_MIN;

}