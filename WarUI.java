/*
 * Kyle Krstulich
 * 10/18/23
 * WarUI.java
 * 
 */
import java.awt.Font;
public class WarUI {

    //Constants coords for drawing to screen
    final static double[] PLAYER_BF_COORDS = {.5,-.5};
    final static double[] CPU_BF_COORDS = {.5,.5};
    final static double[] PLAYER_DECK_COORDS = {-.5, -.5};
    final static double[] CPU_DECK_COORDS = {-.5, .5};
    final static String BACKGROUND_PATH = "./WarAssets/background_1.png";
    private final static int BACK_TEXT_SCALE = 40;

    public static int animationTime;

    //Constructor
    public WarUI(int time){
        animationTime = time;
        StdDraw.setTitle("Even Better War!");
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1, 1);
        StdDraw.picture(0, 0, BACKGROUND_PATH);
    }
    
    private static void draw_text(double[] coords, String text){
        double x = coords[0];
        double y = coords[1];
        StdDraw.setPenColor(StdDraw.WHITE);
        Font textFont = new Font("Times New Roman", Font.PLAIN, BACK_TEXT_SCALE);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, text);
    }

    public static String decode_card(int card){
        String[] suits = {"spade", "club", "heart", "diamond"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + 2<<2);
        String cardAsString = faces[cardFace]+suits[cardSuit];
        return cardAsString;
    }

    private static void draw_card_to_canvas(int card, double[] coords){
        double x = coords[0];
        double y = coords[1];
        String cardPath = "./WarAssets/" + decode_card(card) + ".png";
        StdDraw.picture(x, y, cardPath);

    }

    private static void draw_decks(int[] playerDeck, int[] cpuDeck){
        String playerSize = String.valueOf(BetterWar.get_length(playerDeck));
        String cpuSize = String.valueOf(BetterWar.get_length(cpuDeck));

        String playerBackPath = "./WarAssets/card_back_rect_2.png";
        String cpuBackPath = "./WarAssets/card_back_rect_1.png";

        double playerX = PLAYER_DECK_COORDS[0];
        double playerY = PLAYER_DECK_COORDS[1];
        double cpuX = CPU_DECK_COORDS[0];
        double cpuY = CPU_DECK_COORDS[1];

        StdDraw.picture(cpuX, cpuY, cpuBackPath);
        StdDraw.picture(playerX, playerY, playerBackPath);


        StdDraw.setPenColor(StdDraw.BLACK);
        draw_text(CPU_DECK_COORDS, cpuSize);
        draw_text(PLAYER_DECK_COORDS, playerSize);
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
        StdDraw.picture(0, 0, BACKGROUND_PATH);
        StdDraw.pause(animationTime);
        StdDraw.show();
    }

    public static void draw_game(int[] bf, int[] player, int[] cpu){
        draw_decks(player, cpu);
        draw_battlefield(bf);

        show_to_canvas();
    }

    public static void draw_winner(String winner){
        String winnerCompliled = winner+" Wins!";
        double[] coords = {0,0};
        draw_text(coords,winnerCompliled);

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
