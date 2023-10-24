/*
 * Kyle Krstulich
 * 10/18/23
 * WarUI.java
 * TODO Documentation 
 */
import java.awt.Font;
public class WarUI {

    //Static variables
    final static double BIG_CARD_SCALE = .35;
    final static double SMALL_CARD_SCALE = .15;
    final static int BIG_CARD_TEXT_SCALE = 90;
    final static int SMALL_CARD_TEXT_SCALE = 40;
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
    private static void draw_text(double x, double y, int scale, String card_value){
        Font textFont = new Font("Times New Roman", Font.PLAIN, scale);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, card_value);
    }
    private static void draw_text(double[] coords, int deck_size){
        double x = coords[0];
        double y = coords[1];
        Font textFont = new Font("Times New Roman", Font.PLAIN, SMALL_CARD_TEXT_SCALE);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, Integer.toString(deck_size));
    }
    private static String[] card_to_array(int card){
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + 2<<2);
        String[] card_array = {faces[cardFace],suits[cardSuit]}; //value, suit
        return card_array;
    }

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

    private static void show_to_canvas(){
        StdDraw.pause(animationTime);
        StdDraw.show();
    }

    public static void draw_game(int[] bf, int[] player, int[] cpu){
        StdDraw.clear();

        draw_decks(player, cpu);
        draw_battlefield(bf);

        show_to_canvas();
    }

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
