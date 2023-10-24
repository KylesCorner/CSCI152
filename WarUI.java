/*
 * Kyle Krstulich
 * 10/18/23
 * WarUI.java
 */
import java.awt.Font;
public class WarUI {

    //Constants for drawing to the screen
    final static double BIG_CARD_SCALE = .35;
    final static double SMALL_CARD_SCALE = .15;
    final static int BIG_CARD_TEXT_SCALE = 90;
    final static int SMALL_CARD_TEXT_SCALE = 40;

    //Constants coords for drawing to screen
    final static double[] PLAYER_BF_COORDS = {0,-.6};
    final static double[] CPU_BF_COORDS = {0,.6};
    final static double[] PLAYER_DECK_COORDS = {-.6, -.6};
    final static double[] CPU_DECK_COORDS = {.6, .6};

    public static int animationTime;

    //Constructor
    public WarUI(int time){
        animationTime = time;
        StdDraw.setTitle("Even Better War!");
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1, 1);
    }
    /*
    Description:
        Draws a filled diamond shape at the specified coordinates (x, y) on a graphics canvas using the StdDraw library. The size of the diamond is determined by the yScale parameter,
        and its horizontal size is derived from the yScale by halving it.

    Parameters:
        x (double): The x-coordinate of the center of the diamond.
        y (double): The y-coordinate of the center of the diamond.
        yScale (double): The scaling factor that determines the size of the diamond. It represents the distance from the center of the diamond to any of its four corners.

    Returns:
        None

    This method utilizes the StdDraw library to draw a filled diamond shape on a graphics canvas. The x and y parameters specify the center of the diamond, while the yScale parameter determines 
    the distance from the center to any of its four corners. The horizontal size of the diamond is derived from yScale by halving it. The color of the diamond is set to red, and it is drawn as a 
    filled polygon with its vertices calculated based on the center coordinates and scaling factor. The method does not return a value; it is responsible for drawing the diamond on the canvas. 
    */
    private static void draw_diamond(double x, double y, double yScale){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.RED);
        double xScale = yScale/2;
        double[] xPointsToDraw = {
            x+xScale,
            x,
            x-xScale,
            x
        };
        double[] yPointsToDraw = {
            y,
            y+yScale,
            y,
            y-yScale
        };

        StdDraw.filledPolygon(xPointsToDraw, yPointsToDraw);
    }
    /*
    Description:
        Draws a filled heart shape at the specified coordinates (x, y) on a graphics canvas using the StdDraw library. The size of the heart is determined by the yScale parameter, and its horizontal size 
        is derived from the yScale by halving it.

    Parameters:
        x (double): The x-coordinate of the center of the heart.
        y (double): The y-coordinate of the center of the heart.
        yScale (double): The scaling factor that determines the size of the heart. It represents the distance from the center of the heart to its tips.

    Returns:
        None

    This method utilizes the StdDraw library to draw a filled heart shape on a graphics canvas. The x and y parameters specify the center of the heart, while the yScale parameter determines the distance from the center to 
    the tips of the heart. The horizontal size of the heart is derived from yScale by halving it. The color of the heart is set to red, and it is drawn using two filled circles and a filled polygon. The circles represent 
    the upper halves of the heart, and the polygon represents the bottom half. The method does not return a value; it is responsible for drawing the heart on the canvas.
    */
    private static void draw_heart(double x, double y, double yScale){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.RED);
        double xScale = yScale/2;

        //{x,y,r}
        double[] leftCircle = {
            x-(xScale/2),
            y+(yScale*.75),
            xScale/2
        };
        //{x,y,r}
        double[] rightCircle= {
            x+(xScale/2),
            y+(yScale*.75),
            xScale/2
        };

        double[] xValuesForBottom = {
            x-xScale,
            x,
            x+xScale
        };
        double[] yValuesForBottom = {
            leftCircle[1],
            y-yScale,
            rightCircle[1]
        };


        StdDraw.filledCircle(leftCircle[0], leftCircle[1], leftCircle[2]);
        StdDraw.filledCircle(rightCircle[0], rightCircle[1], rightCircle[2]);
        StdDraw.filledPolygon(xValuesForBottom, yValuesForBottom);

    }
    /*
    Description:
        Draws a filled club (clover) shape at the specified coordinates (x, y) on a graphics canvas using the StdDraw library. The size of the club is determined by the yScale parameter, and 
        its horizontal size is derived from the yScale by halving it.

    Parameters:
        x (double): The x-coordinate of the center of the club.
        y (double): The y-coordinate of the center of the club.
        yScale (double): The scaling factor that determines the size of the club. It represents the distance from the center of the club to its tips.

    Returns:
        None

    This method utilizes the StdDraw library to draw a filled club (clover) shape on a graphics canvas. The x and y parameters specify the center of the club, while the yScale parameter determines the distance 
    from the center to its tips. The horizontal size of the club is derived from yScale by halving it. The color of the club is set to black, and it is drawn using three filled circles and a filled rectangle. 
    The circles represent the upper parts of the club, and the rectangle represents the stem. The method does not return a value; it is responsible for drawing the club on the canvas.
     */
    private static void draw_club(double x, double y, double yScale){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
        double xScale = yScale/2;
        double rectHalfWidth = xScale/2;
        double rectHalfHeight = yScale/2;

        //{x,y,r}
        double[] topCircle = {
            x,
            y+(yScale/2),
            xScale
            
        };
        double[] rightCircle = {
            x+(xScale/2),
            y+(xScale/2),
            xScale
        };
        double[] leftCircle= {
            x-(xScale/2),
            y+(xScale/2),
            xScale
        };


        StdDraw.filledCircle(topCircle[0], topCircle[1], topCircle[2]);
        StdDraw.filledCircle(rightCircle[0], rightCircle[1], rightCircle[2]);
        StdDraw.filledCircle(leftCircle[0], leftCircle[1], leftCircle[2]);
        StdDraw.filledRectangle(x, y-(yScale/2), rectHalfWidth, rectHalfHeight);
    }
    /*
    Description:
        Draws a filled spade shape at the specified coordinates (x, y) on a graphics canvas using the StdDraw library. The size of the spade is determined by the yScale parameter, and its horizontal 
        size is derived from the yScale by halving it.

    Parameters:
        x (double): The x-coordinate of the center of the spade.
        y (double): The y-coordinate of the center of the spade.
        yScale (double): The scaling factor that determines the size of the spade. It represents the distance from the center of the spade to its tips.

    Returns:
        None

    This method utilizes the StdDraw library to draw a filled spade shape on a graphics canvas. The x and y parameters specify the center of the spade, while the yScale parameter determines the distance from the 
    center to its tips. The horizontal size of the spade is derived from yScale by halving it. The color of the spade is set to black, and it is drawn using three filled circles, a filled triangle, and a filled 
    rectangle. The circles represent the upper parts of the spade, the triangle represents the top part, and the rectangle represents the stem. The method does not return a value; it is responsible for drawing 
    the spade on the canvas.
     */
    private static void draw_spade(double x, double y, double yScale){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
        double xScale = yScale/2;
        double rectHalfWidth = xScale/2;
        double rectHalfHeight = yScale/2;

        //{x,y,r}
        double[] leftCircle = {
            x-(xScale/2),
            y,
            xScale/2
        };
        double[] rightCircle= {
            x+(xScale/2),
            y,
            xScale/2
        };

        double[] xValuesForTopTriangle = {
            x,
            x+xScale,
            x-xScale
        };
        double[] yValuesForTopTriangle = {
            y+yScale,
            rightCircle[1],
            leftCircle[1]

        };


        StdDraw.filledPolygon(xValuesForTopTriangle, yValuesForTopTriangle);
        StdDraw.filledCircle(leftCircle[0], leftCircle[1], leftCircle[2]);
        StdDraw.filledCircle(rightCircle[0], rightCircle[1], rightCircle[2]);
        StdDraw.filledRectangle(x, y-(yScale/2), rectHalfWidth, rectHalfHeight);
    }
    /*
    Description:
        Draws text on a graphics canvas using the StdDraw library at the specified coordinates (x, y) with the specified font scale and content (card_value).

    Parameters:
        x (double): The x-coordinate where the text will be drawn.
        y (double): The y-coordinate where the text will be drawn.
        scale (int): The font scale or size for the text.
        card_value (String): The content or text that will be drawn on the canvas.

    Returns:
        None

    This method utilizes the StdDraw library to draw text on a graphics canvas. The x and y parameters specify the coordinates at which the text will be drawn. The scale parameter 
    determines the font size, and card_value specifies the content of the text. The text is drawn using the "Times New Roman" font with the specified size. The method does not return a value; 
    it is responsible for drawing the text on the canvas.
     */
    private static void draw_text(double x, double y, int scale, String card_value){
        Font textFont = new Font("Times New Roman", Font.PLAIN, scale);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, card_value);
    }
    /*
    Description:
    Draws text on a graphics canvas using the StdDraw library at the specified coordinates (coords) and with the specified font scale for displaying the size of a deck (deck_size).

    Parameters:
        coords (double[]): An array containing the x and y coordinates where the text will be drawn.
        deck_size (int): The size of the deck to be displayed as text.

    Returns:
        None

    This method utilizes the StdDraw library to draw text on a graphics canvas. The coords array contains the x and y coordinates where the text will be drawn. The font size is set to SMALL_CARD_TEXT_SCALE, 
    which is presumably defined elsewhere in your code. The text displayed is the size of a deck, specified by deck_size. The method does not return a value; it is responsible for drawing the text on the canvas.
     */
    private static void draw_text(double[] coords, int deck_size){
        double x = coords[0];
        double y = coords[1];
        Font textFont = new Font("Times New Roman", Font.PLAIN, SMALL_CARD_TEXT_SCALE);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, Integer.toString(deck_size));
    }
    /*
    Description:
    Converts a playing card represented as an integer into an array of strings that include the card's face value and suit. This method is specifically designed for converting the card encoding used in your card game simulation.

    Parameters:
        card (int): The integer encoding of the playing card, where bits 0-1 represent the card's suit, and bits 2-3 represent the face value.

    Returns:
        String[]: An array of strings containing the card's face value (e.g., "2", "King") at index 0 and the card's suit (e.g., "Spades", "Diamonds") at index 1.

    This method takes an integer representation of a playing card, extracts its face value and suit, and returns this information as an array of two strings. The faces array and suits array are used for mapping the integer encoding 
    to the actual card value and suit. The method returns an array with the card's face value and suit at the respective indices.
     */
    private static String[] card_to_array(int card){
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + 2<<2);
        String[] card_array = {faces[cardFace],suits[cardSuit]}; //value, suit
        return card_array;
    }
    /*
    Description:
        Draws a playing card on a graphics canvas using the StdDraw library based on the provided card value and coordinates. The card is drawn as a rectangle with the card's face value and suit symbol.

    Parameters:
        card (int): The integer encoding of the playing card, where bits 0-1 represent the card's suit, and bits 2-3 represent the face value.
        coords (double[]): An array containing the x and y coordinates where the card will be drawn on the canvas.

    Returns:
        None

    This method utilizes the StdDraw library to draw a playing card on a graphics canvas. The card parameter specifies the integer encoding of the playing card, and coords contains the x and y coordinates where 
    the card will be drawn. The card is drawn as a rectangle with a face value and a suit symbol. The card's size is determined by the constants BIG_CARD_SCALE and BIG_CARD_TEXT_SCALE, which are presumably 
    defined elsewhere in your code. The color of the card is set to black, and it is filled with light gray. The suit symbol is drawn based on the card's suit, and the face value is displayed on the card. The 
    method does not return a value; it is responsible for drawing the card on the canvas. 
    */
    private static void draw_card_to_canvas(int card, double[] coords){
        double x = coords[0];
        double y = coords[1];
        double yScale = BIG_CARD_SCALE;
        double xScale = yScale/1.5;
        StdDraw.setPenRadius(xScale/10);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(x, y, xScale, yScale);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, xScale, yScale);
        String[] card_array = card_to_array(card);
        String card_suit = card_array[1];
        double[] suit_data = {x+(yScale/4), y, yScale/2}; //x,y,scaling
        double[] text_coords = {x-(yScale/4), y};

        switch(card_suit){
            case "Diamonds":
                draw_diamond(suit_data[0], suit_data[1], suit_data[2]);
                break;
            
            case "Hearts":
                draw_heart(suit_data[0], suit_data[1], suit_data[2]);
                break;
            
            case "Clubs":
                draw_club(suit_data[0], suit_data[1], suit_data[2]);
                break;

            case "Spades":
                draw_spade(suit_data[0], suit_data[1], suit_data[2]);
                break;

        }

        draw_text(text_coords[0],text_coords[1], BIG_CARD_TEXT_SCALE, card_array[0]);
    }
    /*
    Description:
        Draws the backs of playing cards on a graphics canvas using the StdDraw library based on the provided coordinates. The card backs are drawn as rectangles.

    Parameters:
        coords (double[]): An array containing the x and y coordinates where the card backs will be drawn on the canvas.
        
    Returns:
        None

    This method utilizes the StdDraw library to draw the backs of playing cards on a graphics canvas. The coords parameter specifies the x and y coordinates where the card backs will be drawn. The card backs 
    are drawn as rectangles with the specified size based on the constants SMALL_CARD_SCALE and SMALL_CARD_TEXT_SCALE, which are presumably defined elsewhere in your code. The card backs are colored red with a 
    blue-filled rectangle inside. The method does not return a value; it is responsible for drawing the card backs on the canvas.
     */
    private static void draw_card_backs(double[] coords){
        double x = coords[0];
        double y = coords[1];
        double yScale = SMALL_CARD_SCALE;
        double xScale = yScale/1.5;
        
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.rectangle(x, y, xScale, yScale);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(x, y, xScale, yScale);
    }
    /*
    Description:
        Draws the player's and CPU's decks, including the card backs and the number of cards in each deck, on a graphics canvas using the StdDraw library.

    Parameters:
        playerDeck (int[]): An array representing the player's deck of cards.
        cpuDeck (int[]): An array representing the CPU's deck of cards.

    Returns:
        None

    This method utilizes the StdDraw library to draw the player's and CPU's decks on a graphics canvas. It includes the card backs and the number of cards in each deck. The sizes of the 
    decks are determined using the get_length method (presumably defined elsewhere in your code). The card backs are drawn as rectangles with the specified size based on the constants SMALL_CARD_SCALE and SMALL_CARD_TEXT_SCALE. 
    The number of cards in each deck is displayed on the card backs. The method does not return a value; it is responsible for drawing the decks on the canvas.
     */
    private static void draw_decks(int[] playerDeck, int[] cpuDeck){
        int playerSize = BetterWar.get_length(playerDeck);
        int cpuSize = BetterWar.get_length(cpuDeck);

        draw_card_backs(PLAYER_DECK_COORDS);
        draw_card_backs(CPU_DECK_COORDS);

        StdDraw.setPenColor(StdDraw.BLACK);
        draw_text(CPU_DECK_COORDS, cpuSize);
        draw_text(PLAYER_DECK_COORDS, playerSize);
        show_to_canvas();
    }  
    /*
    Description:
    Draws the cards on the battlefield on a graphics canvas using the StdDraw library. The cards are taken from the provided bf array and are displayed for both the player and CPU.

    Parameters:
        bf (int[]): An array representing the cards on the battlefield.

    Returns:
        None

    This method utilizes the StdDraw library to draw the cards from the bf array on a graphics canvas. It separates the cards into two arrays, playerCards and cpuCards, to display them for the player and CPU, 
    respectively. The cards are drawn using the draw_card_to_canvas method. After drawing the cards, the show_to_canvas method is called to display the changes on the canvas. The method does not return a value; 
    it is responsible for drawing the cards on the battlefield.
     */
    private static void draw_battlefield(int[] bf){
        int size = BetterWar.get_length(bf);
        int[] playerCards = new int[size/2];
        int[] cpuCards = new int[size/2];

        for(int i = 0; i < size; i+=2){
            playerCards[i/2] = bf[i];
        }
        for(int i = 1; i < size; i+=2){
            cpuCards[i/2] = bf[i];

        }
        for(int i = 0; i < playerCards.length; i++){
            draw_card_to_canvas(playerCards[i], PLAYER_BF_COORDS);
            draw_card_to_canvas(cpuCards[i], CPU_BF_COORDS);
            show_to_canvas();
        }

    }
    /*
    Description:
        Pauses the graphics canvas to display any changes for a specified duration.

    Parameters:
        None

    Returns:
        None

    This method is responsible for pausing the graphics canvas to display any changes for a specified duration, which is determined by the animationTime constant (presumably defined elsewhere in your code). The StdDraw.show() 
    method is used to show the updated canvas, and the pause duration is set by animationTime. The method does not return a value; it is used to control the animation speed on the canvas. 
    */
    private static void show_to_canvas(){
        StdDraw.pause(animationTime);
        StdDraw.show();
    }
    /*
    Description:
        Draws the entire game state, including the player's deck, the CPU's deck, and the cards on the battlefield, on a graphics canvas using the StdDraw library.

    Parameters:
        bf (int[]): An array representing the cards on the battlefield.
        player (int[]): An array representing the player's deck.
        cpu (int[]): An array representing the CPU's deck.

    Returns:
        None

    This method is responsible for drawing the entire game state, including the player's deck, the CPU's deck, and the cards on the battlefield on a graphics canvas. It first clears the 
    canvas, then calls the draw_decks and draw_battlefield methods to draw the decks and cards on the battlefield. Finally, it uses the show_to_canvas method to display the changes on the canvas. 
    The method does not return a value; it is used to update the visual representation of the game.
     */
    public static void draw_game(int[] bf, int[] player, int[] cpu){
        StdDraw.clear();

        draw_decks(player, cpu);
        draw_battlefield(bf);

        show_to_canvas();
    }
    /*
    Description:
        Draws a message indicating the winner of the game on the graphics canvas using the StdDraw library.

    Parameters:
        winner (String): A string representing the winner of the game.

    Returns:
        None

    This method is responsible for drawing a message indicating the winner of the game on the graphics canvas. It sets the font, color, and text position using the StdDraw library and then calls the show_to_canvas method to display the message. The winner parameter specifies the winner of the game. The method does not return a value; it is used to inform the user of the game's outcome visually.
    */
    public static void draw_winner(String winner){
        Font textFont = new Font("Times New Roman", Font.PLAIN, BIG_CARD_TEXT_SCALE);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(textFont);
        StdDraw.text(0, 0, winner + " Wins!");
        show_to_canvas();
    }
    
    
    public static void main(String[] args) {
        WarUI ui = new WarUI(1000);
        int[] testDeck = BetterWar.generate_deck_as_int();
        int[] testDeck2 = BetterWar.generate_deck_as_int();
        int[] testBF = {10,11,12,13,14,15};

        draw_decks(testDeck, testDeck2);

        draw_card_to_canvas(15, PLAYER_BF_COORDS);
        draw_card_to_canvas(24, CPU_BF_COORDS);


        draw_battlefield(testBF);

        draw_winner("CPU");
    }
}
