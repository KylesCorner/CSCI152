/*
 * Kyle Krstulich
 * 10/28/23
 * WarGame.java
 *
For your practical midterm exam you will be extending your game of war (Lab
3.1).  I would like you to do the following..



 Refactor your initial implementation of War.java to use at least 3 different
 static functions within War.java (not including the main method).  Also, create
 a library called WarLibrary.java in which you implement at least 3 different
 static functions (use this war library in your War.java program).



 Lastly, I would like you to use StdDraw to create a graphical representation of
 your War game simulator.  For each turn I should see a graphical representation
 of each card that the user / cpu draws.  Depending on the result you should
 show who won the last turn as well as how many card(s) each player currently
 has.  The number of seconds the game pauses (StdDraw.pause()) between turns
 should be passed in by the user as a command line argument.

 For your practical midterm exam you will be extending your game of war (Lab
 3.1).  I would like you to do the following..



 Refactor your initial implementation of War.java to use at least 3 different
 static functions within War.java (not including the main method).  Also, create
 a library called WarLibrary.java in which you implement at least 3 different
 static functions (use this war library in your War.java program).



 Lastly, I would like you to use StdDraw to create a graphical representation of
 your War game simulator.  For each turn I should see a graphical representation
 of each card that the user / cpu draws.  Depending on the result you should
 show who won the last turn as well as how many card(s) each player currently
 has.  The number of seconds the game pauses (StdDraw.pause()) between turns
 should be passed in by the user as a command line argument.
 */
public class WarGame {

  static String winner;

  /**
   * Initializes the EvenBetterWarLib game by creating an instance of the game with the given
   * animation time.
   *
   * @param animationTime The duration of animations in milliseconds.
   */
  public static void init(int animationTime) {
    // Create a new instance of the EvenBetterWarLib game
    new EvenBetterWarLib(animationTime);
  }

  /**
   * Checks if either the player or CPU has lost the game by depleting their respective decks. If a
   * player loses, updates the winner and sets card values to 0.
   *
   * @return True if a player has lost, otherwise false.
   */
  public static boolean check_lose() {
    boolean result = false;

    if (EvenBetterWarLib.playerDeck.lose()) {
      // The player has lost
      winner = "CPU";
      EvenBetterWarLib.playerCard = 0;
      EvenBetterWarLib.cpuCard = 0;
      result = true;
    }
    if (EvenBetterWarLib.cpuDeck.lose()) {
      // The CPU has lost
      winner = "Player";
      EvenBetterWarLib.playerCard = 0;
      EvenBetterWarLib.cpuCard = 0;
      result = true;
    }

    return result;
  }

  /**
   * The game loop that continues running until a player loses. In each iteration, it: 1. Checks if
   * a player has lost. 2. Deals new cards to both players. 3. Compares the cards to determine the
   * winner of the round. 4. Draws the game interface.
   *
   * <p>After the loop, it draws the game interface one more time to display the final result.
   */
  public static void game_loop() {
    while (true) {
      // Check if a player has lost; if so, exit the loop
      if (check_lose()) break;

      // Deal new cards to both players
      EvenBetterWarLib.new_cards();

      // Compare the cards and determine the winner of the round
      winner = EvenBetterWarLib.compare_cards();

      // Draw the updated game interface
      EvenBetterWarLib.draw_game(winner);
    }

    // Draw the game interface one more time to display the final result
    EvenBetterWarLib.draw_game(winner);
  }

  public static void main(String[] args) {
    init(Integer.parseInt(args[0]));
    game_loop();

    // Flex Upon Pleabians
  }
}
