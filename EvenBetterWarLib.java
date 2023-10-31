import java.awt.Font;

public class EvenBetterWarLib {

    //Constants coords for drawing to screen
    final static double[] PLAYER_BF_COORDS = {.5,-.5};
    final static double[] CPU_BF_COORDS = {.5,.5};
    final static double[] PLAYER_DECK_COORDS = {-.5, -.5};
    final static double[] CPU_DECK_COORDS = {-.5, .5};
    final static String BACKGROUND_PATH = "./WarAssets/background_1.png";
    private final static int BACK_TEXT_SCALE = 40;

    public static int playerCard, cpuCard;
    public static boolean playersTurn;
    public static Deck playerDeck;
    public static Deck cpuDeck;
    private static Deck masterDeck;
    private static int animationTime;

    public EvenBetterWarLib(int animation){
            StdDraw.setTitle("Even Better War!");
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-1, 1);
            StdDraw.picture(0, 0, BACKGROUND_PATH);

            animationTime = animation;
            playersTurn = true;
            cpuDeck = new Deck(true);
            playerDeck = new Deck(true);
            masterDeck = new Deck(false);


            //filling the players decks
            for(int i = 1; i <= Deck.DECK_LENGTH; i++){
                if(i % 2 == 0)playerDeck.add(masterDeck.pull());
                else cpuDeck.add(masterDeck.pull());
            }



        }

    /*
     * ------------------------------------------
     * Drawing Methods
     * ------------------------------------------
     */
    private static void show_to_canvas(){
            StdDraw.pause(animationTime);
            StdDraw.show();
        }
    private static void draw_text(double[] coords, String text){
        double x = coords[0];
        double y = coords[1];
        StdDraw.setPenColor(StdDraw.WHITE);
        Font textFont = new Font("Times New Roman", Font.PLAIN, BACK_TEXT_SCALE);
        StdDraw.setFont(textFont);
        StdDraw.text(x, y, text);
    }

    private static String decode_card(int card){
        String[] suits = {"spade", "club", "heart", "diamond"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + 2<<2);
        String cardAsString = faces[cardFace]+suits[cardSuit];
        return cardAsString;
    }

    private static void draw_cards(){
        double playerX = PLAYER_BF_COORDS[0];
        double playerY = PLAYER_BF_COORDS[1];
        double cpuX = CPU_BF_COORDS[0];
        double cpuY = CPU_BF_COORDS[1];

        if(cpuCard > 0 && playerCard > 0){

            String playerCardPath = "./WarAssets/" + decode_card(playerCard) + ".png";
            String cpuCardPath = "./WarAssets/" + decode_card(cpuCard) + ".png";

            StdDraw.picture(playerX, playerY, playerCardPath);
            StdDraw.picture(cpuX, cpuY, cpuCardPath);

        }
    }

    private static void draw_decks(){
        String playerSize = String.valueOf(playerDeck.get_length());
        String cpuSize = String.valueOf(cpuDeck.get_length());

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

    public static void draw_game(){

        StdDraw.picture(0, 0, BACKGROUND_PATH);
        draw_decks();
        draw_cards();

        show_to_canvas();
    }

    public static void draw_winner(String winner){
        String winnerCompliled = winner+" Wins!";
        double[] coords = {0,0};
        draw_text(coords,winnerCompliled);
        show_to_canvas();

    }

    /*
     * ------------------------------------------
     * Game Methods
     * ------------------------------------------
     */
    public static void compare_cards(){
        int cpuCardValue = cpuCard>>2;
        int playerCardValue = playerCard>>2;

        if(playerCardValue == cpuCardValue){
            if(playerCard > cpuCard){
                playerDeck.add(playerCard);
                playerDeck.add(cpuCard);
                playersTurn = true;
            }
            if(playerCard < cpuCard){
                cpuDeck.add(playerCard);
                cpuDeck.add(cpuCard);
                playersTurn = false;
            }

        }else{
            if(playerCardValue > cpuCardValue){
                playerDeck.add(playerCard);
                playerDeck.add(cpuCard);
                playersTurn = true;
            }
            if(playerCardValue < cpuCardValue){
                cpuDeck.add(playerCard);
                cpuDeck.add(cpuCard);
                playersTurn = false;
            }
        }
    }

    public static void new_cards(){
        if(playersTurn){
            playerCard = playerDeck.pull();
            cpuCard = cpuDeck.pull();
        }else{
            cpuCard = cpuDeck.pull();
            playerCard = playerDeck.pull();
        }
    }

    public static void main(String[] args) {
        EvenBetterWarLib lib = new EvenBetterWarLib(100);

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
    public static final int DECK_LENGTH = 52;

    private int[] deck = new int[DECK_LENGTH];

    public Deck(boolean empty){
        if(!empty) deck = generate();
    }

    public void output_to_cmd(){
        for(int i = 0; i < deck.length; i++){
            System.out.println(deck[i]);
        }
    }

    public int[] generate(){

        for(int suit = 0; suit < 4; suit++){

            for(int face = 0; face < 13; face++){
                int card = face+2;
                card = (card << 2) + suit;
                deck[13*suit + face] = card;

            }
        }
        shuffle();
        return deck;
    }

    public boolean lose(){
        boolean result = get_length() == 0;
        return result;
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

    public int get_length(){
        int size = deck.length;
        int indexOfFirstValue = 0;

        //getting the index of the first value > 0.
        for(int i = 0; i < size; i++){
            if(deck[i] > 0){
                indexOfFirstValue++;
            }
        }

        return indexOfFirstValue;
    }

    public int get_card(int index){
        return deck[index];
    }

    public int pull(){
        int card;
        int indexOfFirstValue = 0;


        //getting the index of the first value > 0.
        for(int i = 0; i < deck.length; i++){
            if(deck[i] > 0){
                indexOfFirstValue = i;
                break;
            }
        }

        card = deck[indexOfFirstValue];
        deck[indexOfFirstValue] = 0;

        //Shifting values down.
        int actualSize = get_length();
        System.arraycopy(deck, 1, deck, 0, actualSize);
        deck[actualSize] = 0;

        return card;
    }

    public void add(int item_to_append){
        deck[get_length()] = item_to_append;
        shuffle();
    }
}
