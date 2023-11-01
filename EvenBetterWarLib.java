/*
 * Kyle Krstulich
 * 10/28/23
 * EvenBetterWarLib.java
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
import java.awt.Font;

public class EvenBetterWarLib {

    /**
     * Constants and class variables for drawing and managing the EvenBetterWarLib game.
     */

    // Coordinates for player and CPU battlefields
    final static double[] PLAYER_BF_COORDS = {.5, -0.5};
    final static double[] CPU_BF_COORDS = {.5, 0.5};

    // Coordinates for player and CPU deck locations
    final static double[] PLAYER_DECK_COORDS = {-.5, -0.5};
    final static double[] CPU_DECK_COORDS = {-.5, 0.5};

    // File path for the game's background image
    final static String BACKGROUND_PATH = "./WarAssets/background_1.png";

    // Text scaling for background text
    private final static int BACK_TEXT_SCALE = 40;

    // Variables to hold player and CPU card values and determine the current player's turn
    public static int playerCard, cpuCard;
    public static boolean playersTurn;

    // Decks for player, CPU, and the master deck
    public static Deck playerDeck;
    public static Deck cpuDeck;
    private static Deck masterDeck;

    // Duration for animations in milliseconds
    private static int animationTime;


    /**
     * Constructor for the EvenBetterWarLib class. Initializes the game environment and decks.
     *
     * @param animation  The duration of animations in milliseconds.
     */
    public EvenBetterWarLib(int animation) {
        // Set up the game window
        StdDraw.setTitle("Even Better War!");
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1, 1);
        StdDraw.picture(0, 0, BACKGROUND_PATH);
        // Define the font for the text
        Font textFont = new Font("Times New Roman", Font.PLAIN, BACK_TEXT_SCALE);
        StdDraw.setFont(textFont);
        // Set the pen color to white for the text
        StdDraw.setPenColor(StdDraw.WHITE);
        // Set animation time
        animationTime = animation;

        // It's the player's turn at the beginning
        playersTurn = true;

        // Initialize decks
        cpuDeck = new Deck(true);
        playerDeck = new Deck(true);
        masterDeck = new Deck(false);

        // Fill the player and CPU decks
        for (int i = 1; i <= Deck.DECK_LENGTH; i++) {
            if (i % 2 == 0) {
                playerDeck.add(masterDeck.pull());
            } else {
                cpuDeck.add(masterDeck.pull());
            }
        }
    }
    /*
     * ------------------------------------------
     * Drawing Methods
     * ------------------------------------------
     */
    
    /**
     * Pauses the program for a specified duration and displays the current
     * graphics on the canvas.  This method is used to update the game's
     * graphical representation and introduce delays for animations.
     */
    private static void show_to_canvas() {
        // Pause for the specified animation time
        StdDraw.pause(animationTime);

        // Display the updated graphics on the canvas
        StdDraw.show();
    }

    /**
     * Draws text on the canvas at specified coordinates.
     *
     * @param coords  An array containing the x and y coordinates where the text will be drawn.
     * @param text    The text to be displayed on the canvas.
     */
    private static void draw_text(double[] coords, String text) {
        double x = coords[0];
        double y = coords[1];
        // Draw the specified text at the given coordinates
        StdDraw.text(x, y, text);
    }

    /**
     * Decodes an integer representation of a playing card into its corresponding card name.
     *
     * @param card  An integer representing a playing card.
     * @return      The name of the decoded card, e.g., "2spade" or "Qheart".
     */
    private static String decode_card(int card) {
        // Arrays to represent card suits and faces
        String[] suits = {"spade", "club", "heart", "diamond"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        // Extract the card's face and suit information from the integer
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + (2 << 2));

        // Create the card name by combining the face and suit
        String cardAsString = faces[cardFace] + suits[cardSuit];

        // Return the decoded card name
        return cardAsString;
    }

    /**
     * Draws the playing cards on the battlefield for both the player and the CPU.
     * If both player and CPU have cards, their respective cards are displayed on the battlefield.
     */
    private static void draw_cards() {
        // Get the coordinates for the player and CPU battlefields
        double playerX = PLAYER_BF_COORDS[0];
        double playerY = PLAYER_BF_COORDS[1];
        double cpuX = CPU_BF_COORDS[0];
        double cpuY = CPU_BF_COORDS[1];

        // Check if both the player and CPU have cards to display
        if (cpuCard > 0 && playerCard > 0) {
            // Generate file paths for the player's and CPU's cards based on card values
            String playerCardPath = "./WarAssets/" + decode_card(playerCard) + ".png";
            String cpuCardPath = "./WarAssets/" + decode_card(cpuCard) + ".png";

            // Display the player's and CPU's cards on the battlefield
            StdDraw.picture(playerX, playerY, playerCardPath);
            StdDraw.picture(cpuX, cpuY, cpuCardPath);
        }
    }

    /**
     * Draws the decks for both the player and the CPU on the game interface. This includes displaying
     * the number of cards in each player's deck and showing the card backs for their decks.
     */
    private static void draw_decks() {
        // Get the number of cards in the player's and CPU's decks as strings
        String playerSize = String.valueOf(playerDeck.get_length());
        String cpuSize = String.valueOf(cpuDeck.get_length());

        // Define file paths for the card backs for the player's and CPU's decks
        String playerBackPath = "./WarAssets/card_back_rect_2.png";
        String cpuBackPath = "./WarAssets/card_back_rect_1.png";

        // Get the coordinates for the player's and CPU's deck locations
        double playerX = PLAYER_DECK_COORDS[0];
        double playerY = PLAYER_DECK_COORDS[1];
        double cpuX = CPU_DECK_COORDS[0];
        double cpuY = CPU_DECK_COORDS[1];

        // Display the card backs for the player's and CPU's decks
        StdDraw.picture(cpuX, cpuY, cpuBackPath);
        StdDraw.picture(playerX, playerY, playerBackPath);

        // Set the pen color to black and display the number of cards in each deck
        StdDraw.setPenColor(StdDraw.BLACK);
        draw_text(CPU_DECK_COORDS, cpuSize);
        draw_text(PLAYER_DECK_COORDS, playerSize);
    }

    /**
     * Draws the winner's information on the game interface.
     *
     * @param winner  A string representing the name of the game's winner or an indication of a draw.
     */
    private static void draw_winner(String winner) {
        // Compile the winner's name with a "Wins!" suffix
        String winnerCompiled = winner + " Wins!";
        
        // Coordinates for the text display (centered on the canvas)
        double[] coords = {0, 0};
        
        // Draw the winner's information on the canvas
        draw_text(coords, winnerCompiled);
    }
    
    /**
     * Draws the game interface, including the background, the game winner's information, player decks,
     * and playing cards on the battlefield.
     *
     * @param winner  A string representing the name of the game's winner or an indication of a draw.
     */
    public static void draw_game(String winner) {
        // Display the game's background
        StdDraw.picture(0, 0, BACKGROUND_PATH);
        
        // Draw the game winner's information
        draw_winner(winner);
        
        // Draw the decks for both the player and the CPU
        draw_decks();
        
        // Draw the playing cards on the battlefield
        draw_cards();
        
        // Show the updated graphics on the canvas
        show_to_canvas();
    }


    /*
     * ------------------------------------------
     * Game Methods
     * ------------------------------------------
     */

    /**
     * Compares the values of the player's and CPU's cards and determines the
     * winner of the current round.  If the player wins, their card is added to
     * their deck. If the CPU wins, the card is added to their deck.  If it's a
     * tie, the cards remain on the battlefield, and the current player's turn
     * is determined.
     *
     * @return  A string indicating the winner of the round ("Player" or "CPU").
     */
    public static String compare_cards() {
        // Extract the face values of the player's and CPU's cards
        int cpuCardValue = cpuCard >> 2;
        int playerCardValue = playerCard >> 2;
        
        // Initialize the result with "Player"
        String result = "Player";

        // Compare the face values of the cards
        if (playerCardValue == cpuCardValue) {
            if (playerCard > cpuCard) {
                // Player wins and adds both cards to their deck
                playerDeck.add(playerCard);
                playerDeck.add(cpuCard);
                playersTurn = true;
            }
            if (playerCard < cpuCard) {
                // CPU wins and adds both cards to its deck
                cpuDeck.add(playerCard);
                cpuDeck.add(cpuCard);
                playersTurn = false;
                result = "CPU";
            }
        } else {
            if (playerCardValue > cpuCardValue) {
                // Player wins and adds both cards to their deck
                playerDeck.add(playerCard);
                playerDeck.add(cpuCard);
                playersTurn = true;
            }
            if (playerCardValue < cpuCardValue) {
                // CPU wins and adds both cards to its deck
                cpuDeck.add(playerCard);
                cpuDeck.add(cpuCard);
                playersTurn = false;
                result = "CPU";
            }
        }

        // Return the result indicating the winner of the round
        return result;
    }

    /**
     * Deals new cards for both the player and the CPU. The cards are drawn from
     * their respective decks, and the current player's turn is considered for
     * the card dealing order.
     */
    public static void new_cards() {
        if (playersTurn) {
            // If it's the player's turn, draw a card for the player first, then for the CPU
            playerCard = playerDeck.pull();
            cpuCard = cpuDeck.pull();
        } else {
            // If it's the CPU's turn, draw a card for the CPU first, then for the player
            cpuCard = cpuDeck.pull();
            playerCard = playerDeck.pull();
        }
    }


    public static void main(String[] args) {
        new EvenBetterWarLib(100);

        System.out.println("--Player Deck--" + playerDeck.get_length());
        playerDeck.output_to_cmd();

        System.out.println("--CPU Deck--" + cpuDeck.get_length());
        cpuDeck.output_to_cmd();

        System.out.println("--Master Deck--" + masterDeck.get_length());
        masterDeck.output_to_cmd();

        // masterDeck = new Deck(false);
        // playerDeck = new Deck(true);

        // playerDeck.add(masterDeck.pull());

        // playerDeck.output_to_cmd();


        
    }
    
}

class Deck {
    /**
     * A class representing a deck of playing cards.
     */

    // The total length of a standard deck of 52 cards
    public static final int DECK_LENGTH = 52;

    // An array to hold the card values in the deck
    private int[] deck = new int[DECK_LENGTH];


    public Deck(boolean empty){
        if(!empty) deck = generate();
    }

    /**
     * Generates a standard deck of 52 playing cards and shuffles them.
     *
     * @return An array of integers representing a standard deck of playing
     *         cards, where each integer encodes both the face and suit of a card.
     * 
     * The encoding format is as follows:
     * Bits 0-1: Represent the card's suit (0 for Spades, 1 for Clubs, 2 for Hearts, 3 for Diamonds).
     * Bits 2-3: Represent the card's face value (ranging from 2 to Ace).
     */
    public int[] generate() {
        for (int suit = 0; suit < 4; suit++) {
            for (int face = 0; face < 13; face++) {
                int card = face + 2;
                card = (card << 2) + suit;
                deck[13 * suit + face] = card;
            }
        }
        
        // Shuffle the generated deck of cards
        shuffle();
        
        // Return the shuffled deck
        return deck;
    }

    /**
     * Calculates and returns the current length of the deck by counting non-zero values.
     *
     * @return The number of non-zero elements in the deck, representing the current deck length.
     */
    public int get_length() {
        int size = deck.length;
        int indexOfFirstValue = 0;

        // Calculate the index of the first non-zero value in the deck
        for (int i = 0; i < size; i++) {
            if (deck[i] > 0) {
                indexOfFirstValue++;
            }
        }

        // Return the current deck length based on non-zero elements
        return indexOfFirstValue;
    }

    /**
     * Pulls a card from the deck, removing it from the deck, and shifts the remaining cards down.
     *
     * @return The value of the pulled card.
     */
    public int pull() {
        int card;
        int indexOfFirstValue = 0;

        // Find the index of the first non-zero value in the deck
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] > 0) {
                indexOfFirstValue = i;
                break;
            }
        }

        // Get the value of the pulled card and mark it as removed (set to 0)
        card = deck[indexOfFirstValue];
        deck[indexOfFirstValue] = 0;

        // Shift the remaining values down in the deck
        int actualSize = get_length();
        System.arraycopy(deck, 1, deck, 0, actualSize);
        deck[actualSize] = 0;

        return card;
    }

    /**
     * Adds a card to the deck and shuffles the deck to maintain randomness.
     *
     * @param item_to_append  The card to be added to the deck.
     */
    public void add(int item_to_append) {
        // Add the card to the deck at the end
        deck[get_length()] = item_to_append;
        
        // Shuffle the deck to maintain randomness
        shuffle();
    }

    public boolean lose(){
        boolean result = get_length() == 0;
        return result;
    }

    public void output_to_cmd(){
        for(int i = 0; i < deck.length; i++){
            System.out.println(deck[i]);
        }
    }

    public void shuffle(){
        int randomIndex,temp;
        int size = get_length()-1;
        

        if(size > 1){
            for(int i = size; i >= 0; i--){
                randomIndex = (int)Math.floor((Math.random() * i)) + 1;
                temp = deck[randomIndex];
                deck[randomIndex] = deck[i];
                deck[i] = temp;
                
            }
        }

    }

    public int get_card(int index){
        return deck[index];
    }
}
