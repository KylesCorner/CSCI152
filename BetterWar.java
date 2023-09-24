/*
 * Kyle Krstulich
 * 9/20/23
 * BetterWar.java
 * 
 * Read from the command line the number of times that the user would like to play.  Please use explicit casting to take the users string value and convert it into an integer.  
 * If the user enters an invalid value i.e. a number less than or equal to 0, the program should print out an error message and terminate
 * 
 *  Assuming we know the number of times the user has requested to play the game, use the correct type of loop to handle each iteration of the game.
 * 
 *  For each game the player that flips their card first should be randomly assigned (for the first game).  Each game thereafter, the winner of the previous game should have their card revealed first for the remainder of that game.
 * 
 *  You should use Arrays to keep track of the state of each players deck i.e. the cards that they have in the current iteration.
 * 
 * When a player (user or the CPU) has 0 cards remaining they are declared the loser, and the next game should begin (if the user agrees to play again) i.e. use Scanner to get input from the user, and determine if they would like to 
 * play again.  If they answer "yes", the next iteration of the game should begin.  Otherwise the program should terminate, and thank the user for playing.
 * 
 * In the case of a tie, the game should default to the following scoring system ("diamonds", "hearts", "clubs", "spades") with diamonds beating hearts, hearts beating clubs, etc..
 * 
 * When the games have completed the program should print out the total number of wins for each player, as well as the total number of card flips for each game (don't count the tie as an additional card flip).
 

 */

import java.util.Scanner;

public class BetterWar {

    // Final globals
    final static int DECK_LENGTH = 52;

    // Static globals
    static int[] deck = generate_deck_as_int();
    static int[] player1 = new int[DECK_LENGTH];
    static int[] player2 = new int[DECK_LENGTH];
    static int[] wins = new int[2];
    static int[] battlefield = new int[DECK_LENGTH]; //cards in play.
    static int cardsFlipped = 0;
    static int numberOfGamesPlayed = 0;
    static int numberOfGames = 0;
    // Initial turn randomly choose who plays first card.
    static boolean turn = Math.floor(Math.random()*2)+1 == 1; // True for player 1; False for player 2.

    /*
    Description:
        Generates and returns a standard deck of playing cards as an array of integers. Each integer in the array represents a card with a unique value based on its face and suit. 
        The deck contains a total of 52 cards, with values ranging from 2 to 14 (representing numbered cards 2 through 10, and face cards Jack, Queen, King, Ace). The suits are
        represented as integers from 0 to 3, where 0 corresponds to "Spades," 1 to "Clubs," 2 to "Hearts," and 3 to "Diamonds." The resulting array is ordered such that the cards 
        are grouped by suit and sorted by face value. Shuffles the deck.

    Parameters:
        None

    Returns:

        int[]: An array of integers representing a standard deck of playing cards, where each integer encodes both the face and suit of a card. The encoding format is as follows:

    Bits 0-1: Represent the card's suit (0 for Spades, 1 for Clubs, 2 for Hearts, 3 for Diamonds).
    Bits 2-3: Represent the card's face value (ranging from 2 to Ace).
    The resulting array has a length of 52, with each element representing a unique card in the deck.
     */
    public static int[] generate_deck_as_int(){
        int[] deck = new int[DECK_LENGTH];

        for(int suit = 0; suit < 4; suit++){

            for(int face = 0; face < 13; face++){
                int card = face+2;
                card = (card << 2) + suit;
                deck[13*suit + face] = card;

            }
        }
        shuffle_deck(deck);
        return deck;
    }


    /*
    Description:
        Outputs the human-readable representation of a single playing card, given its integer encoding. This method takes an integer card as input,
        where the card's face value and suit are encoded, and prints the card's name in the format "Face of Suit" (e.g., "Ace of Spades").

    Parameters:

        card (int): An integer encoding representing a playing card. The encoding format is as follows:
        Bits 0-1: Represent the card's suit (0 for Spades, 1 for Clubs, 2 for Hearts, 3 for Diamonds).
        Bits 2-3: Represent the card's face value (ranging from 2 to Ace).

    Returns:
        None

    The method prints the card's name to the console but does not return any value.
    This method is useful for converting the numeric representation of a card into a user-friendly format for display.
     */
    private static void output_single_card(int card){
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int cardFace = (card >> 2) - 2;
        int cardSuit = card ^ (cardFace + 2<<2);

        System.out.println(faces[cardFace] +  " of " + suits[cardSuit]);
    }

    /*
    Description:
        Outputs the human-readable representation of an array of playing cards, given an array of integer encodings representing
        the cards. Each integer in the array encodes both the card's suit and face value, and this method prints the names of all 
        the cards in the array. Skipping all elements that = 0. 0 being a null value.

    Parameters:

        deck (int[]): An array of integers representing a deck of playing cards. Each integer in the array encodes both the card's 
        suit and face value. The encoding format is as follows:
        Bits 0-1: Represent the card's suit (0 for Spades, 1 for Clubs, 2 for Hearts, 3 for Diamonds).
        Bits 2-3: Represent the card's face value (ranging from 2 to Ace).

    Returns:
        None

    The method iterates through the array of integer-encoded cards and uses the output_single_card method to print each card's name. 
    After printing all the cards, it adds a line of dashes to separate the output for clarity.
    This method is useful for displaying the contents of a deck of cards in a human-readable format, facilitating visualization and 
    debugging while correctly considering the bit encoding of the card's suit and face value.
     */
    private static void output_int_array_as_cards(int[] arr){

        int size = get_length(arr);


        for(int i = 0; i < size; i++){
            output_single_card(arr[i]);
            
        }
        System.out.println("-----------------------------------------------------------------");
    }

    /*
    Description:
        Outputs the elements of an integer array to the console, one element per line. After displaying all the elements, it adds a 
        line of dashes to separate the output for clarity.

    Parameters:

        arr (int[]): An integer array containing the elements to be displayed.

    Returns:
        None

    The method iterates through the elements of the input array arr and prints each element's value to the console on a separate line. 
    This method is useful for debugging and displaying the contents of an integer array.
     */
    private static void output_array(int[] arr){
        int size = arr.length;

        for(int i = 0; i < size; i++){
            System.out.println(arr[i]);
        }
        System.out.println("-----------------------------------------------------------------");
    }

    /*
    Description:
        Appends an integer element to the end of an existing integer array, replacing the first occurrence of the value 0 in the array with the new element. This method modifies the original array in place.

    Parameters:

        arr (int[]): The original integer array to which the element will be appended. The first occurrence of the value 0 in this array will be replaced with item_to_append.
        item_to_append (int): The integer element to append to the array, replacing the first occurrence of 0.

    Returns:
        None

    This method searches for the first occurrence of the value 0 in the original array arr and replaces it with the item_to_append. It modifies the original array in place, and no new array is returned.
     */
    private static void append_array(int[] arr, int item_to_append){

        int size = arr.length;
        int indexOfFirstValue = 0;
        //getting the index of the first value == 0.
        for(int i = 0; i < size; i++){
            if(arr[i] == 0){
                indexOfFirstValue = i;
                break;
            }
        }

        arr[indexOfFirstValue] = item_to_append;
        
    }

    /*
    Description:
        Pulls and returns the first positive (greater than 0) integer value from an integer array and shifts the remaining values down to fill the gap. This method modifies the original array in place.

    Parameters:

        arr (int[]): The original integer array from which the first positive value will be pulled.

    Returns:

        int: The positive integer value that was pulled from the original array.

    This method searches for the first positive integer value (greater than 0) in the original array arr and removes it, replacing it with 0. It then shifts the remaining values down to fill the gap created 
    by the pulled value. The method returns the positive integer value that was pulled from the array. The original array is modified in place. 
     */
    private static int pull_first(int[] arr){
        int size = arr.length;
        int indexOfFirstValue = 0;
        int card;

        //getting the index of the first value > 0.
        for(int i = 0; i < size; i++){
            if(arr[i] > 0){
                indexOfFirstValue = i;
                break;
            }
        }
        card = arr[indexOfFirstValue];
        arr[indexOfFirstValue] = 0;

        //Shifting values down.
        int actualSize = get_length(arr);
        System.arraycopy(arr, 1, arr, 0, actualSize);
        arr[actualSize] = 0;

        return card;
    }

    /*
    Description:
        Calculates and returns the number of positive (greater than 0) integer values in an integer array. This method does not modify the original array.

    Parameters:

        arr (int[]): The integer array for which the length of positive values will be determined.

    Returns:

    int: The number of positive integer values (greater than 0) in the array.
    This method iterates through the elements of the original array arr and counts the number of positive integer values. It then returns the count, indicating 
    the length of the array containing positive values. The original array is not modified by this method.
     */
    private static int get_length(int[] arr){
        int size = arr.length;
        int indexOfFirstValue = 0;

        //getting the index of the first value > 0.
        for(int i = 0; i < size; i++){
            if(arr[i] > 0){
                indexOfFirstValue++;
            }
        }

        return indexOfFirstValue;
    }

    /*
    Description:
        Shuffles the order of elements in an integer array representing a deck of cards using the Fisher-Yates shuffle algorithm. This method modifies the original array in place.

    Parameters:

        deck (int[]): The integer array representing a deck of cards to be shuffled.

    Returns:
        None

    This method implements the Fisher-Yates shuffle algorithm to randomize the order of elements in the original deck array. It iterates through the array in reverse order, 
    selecting a random index between 0 and the current index (inclusive), swaps the elements at those indices, and continues until all elements have been shuffled. The original deck array is modified in place.
     */
    private static void shuffle_deck(int[] deck){
        int randomIndex,temp;
        int size = get_length(deck)-1;
        

        for(int i = size; i >= 0; i--){
            randomIndex = (int)Math.floor((Math.random() * i)) + 1;
            temp = deck[randomIndex];
            deck[randomIndex] = deck[i];
            deck[i] = temp;
            
        }

    }

    /*
    Description:
        Simulates the initial deal of cards in a card game by pulling the top 26 cards from a shuffled deck and returning them in a new integer array. The original deck is modified as cards are pulled.

    Parameters:

        deck (int[]): The integer array representing a shuffled deck of cards from which the initial cards will be dealt.

    Returns:

        int[]: A new integer array containing the top 26 cards from the shuffled deck, representing the initial deal.

    This method starts by creating a new integer array output of size 26 to represent the initial deal. It then iteratively pulls the top card from the deck using the pull_first method and adds it to the 
    output array. After pulling all 26 cards, it shuffles the output array to randomize the order of the dealt cards. Finally, it returns the output array, which represents the initial deal of cards. The 
    original deck is modified as cards are pulled.
     */
    private static int[] inital_deal(int[] deck){
        int[] output = new int[DECK_LENGTH];
        int size = 26;

        for(int i = 0; i < size; i++){
            output[i] = pull_first(deck);
        }

        return output;
    }

    /*
    Description:
        Simulates a round of card comparison in a card game (e.g., War) between two players. The method manages the comparison of two cards, updates the battlefield, 
        and determines the winner of the round. In case of a tie, it initiates a "war" by recursively calling itself.

    Parameters:
        None

    Returns:
        None

    This method simulates a round of card comparison between two players in a card game. It starts by determining which player plays their card first based on the turn 
    variable. It pulls the top card from each player's deck, appends them to the battlefield array, and displays the cards played with a nice output to the command line.

    The method then compares the face values of the cards on the battlefield to determine the winner. If player 1 wins, their cards are appended to their deck and shuffled. 
    If player 2 wins, their cards are appended to their deck and shuffled. In the case of a tie, a "war" is initiated by recursively calling the compare_cards method.
     
    This method effectively handles the logic of comparing cards, resolving ties with wars, and updating players' decks based on the outcome of each round in the card game simulation.
     */
    private static void compare_cards(){
        int card1,card2;

        // After initial turn winner plays first card.
        if(turn){
        card1 = pull_first(player1);
        card2 = pull_first(player2);
        }else{
        card2 = pull_first(player2);
        card1 = pull_first(player1);
        }
        append_array(battlefield, card1);
        append_array(battlefield, card2);

        // Nice output to command line
        // output_single_card(card1);
        // System.out.println("Versus");
        // output_single_card(card2);
        // System.out.println("----------------------------------------------------");

        // Winner appends cards to deck. Shuffle.
        if(battlefield[get_length(battlefield)-2] > battlefield[get_length(battlefield)-1] ){// player 1 wins.
            int size = get_length(battlefield);
            for(int i = 0; i < size; i++){
                append_array(player1, battlefield[i]);
            }
            shuffle_deck(player1);
        } 
        if(battlefield[get_length(battlefield)-2] < battlefield[get_length(battlefield)-1] ){// player 2 wins.
            int size = get_length(battlefield);
            for(int i = 0; i < size; i++){
                append_array(player2, battlefield[i]);
            }
            shuffle_deck(player2);
        } 
        if(battlefield[get_length(battlefield)-2] == battlefield[get_length(battlefield)-1] ){// players are equal. Go to war!
            System.out.println("War!");
            compare_cards();
        }

    }


    /*
    Description:
        Prompts the user to decide whether to play another game and handles the game continuation or termination based on user input. If the user chooses to continue playing 
        ('y'), the method returns control to the game. If the user chooses to quit ('n'), the method displays a thank you message and exits the program. Also prints game stats.
    Parameters:
        None

    Returns:
        None

    This method uses a Scanner to obtain user input regarding whether they want to play another game. If the user enters 'n', indicating they do not want to play another game, 
    the method displays a thank you message, calls output_stats to display game statistics (such as the number of wins and cards played), and exits the program. 
    If the user enters 'y', indicating they want to play another game, the method returns control to the game for the next iteration. 
     */
    private static void play_again(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Next game? y or n?");
        char response = sc.next().charAt(0);

        if(response == 'n'){
            System.out.println("Thanks for playing!");
            // Output number of wins and cards played.
            output_stats();
            System.exit(0);
        }
    }

    /*
     * Prints the stats of the game to the command-line.
     */
    private static void output_stats(){
        System.out.println("Player won " + wins[0] + " times.\n" + 
        "CPU won " + wins[1] + " times.\n" + 
        "Number of cards flipped: " + cardsFlipped);
    }

    /*
    Description:
        Simulates a card game (e.g., War) for a specified number of games, where two players compete. The game consists of rounds where players 
        are dealt cards, and the player who runs out of cards first loses. The method manages the game loop, keeps track of wins, and allows the 
        user to play multiple games or exit.

    Parameters:

        numberOfGames (int): The total number of games to be played in the simulation.

    Returns:
        None

    This method sets up the game by dealing cards to two players, player1 and player2, from a shuffled deck. It then enters a loop that runs for the 
    specified number of games. Within each game, it enters another loop that continues until one of the players runs out of cards. If a player runs out of 
    cards, it declares the winner, resets the game, and prompts the user to play again. The method also keeps track of the number of games played and cards flipped in each game.

    The inner loop manages turns and calls the compare_cards method to determine the outcome of each round and resets the battlefield. After each game, the method checks if the specified number of 
    games have been played or if the user chooses to quit. If the user chooses to quit or the number of games is satisfied, the method exits the game loop. 
     */
    public static void game(int numberOfGames){

        // Game Setup
        player1 = inital_deal(deck);
        player2 = inital_deal(deck);


        // Repeat till number of games are satisfied.
        for(int i = 0; i < numberOfGames; i++){
            while(true){

                // Player left with no cards looses.
                if(get_length(player1) == 0){
                    System.out.println("CPU wins!");
                    turn = false;
                    wins[1]++;
                    deck = generate_deck_as_int();
                    player1 = inital_deal(deck);
                    player2 = inital_deal(deck);
                    numberOfGamesPlayed++;
                    // Prompt user to play again.
                    play_again();
                    break;
                    
                }
                if(get_length(player2) == 0){
                    System.out.println("Player wins!");
                    turn = true;
                    wins[0]++;
                    deck = generate_deck_as_int();
                    player1 = inital_deal(deck);
                    player2 = inital_deal(deck);
                    numberOfGamesPlayed++;
                    // Prompt user to play again.
                    play_again();
                    break;
                }

                compare_cards();
                cardsFlipped+= 2;
                // reseting the battlefield
                int size = get_length(battlefield);
                for(int b = 0; b < size; b++){
                    battlefield[b] = 0;
                }

            }

        }
        output_stats();
    }

    public static void main(String[] args) {


        // Read command-line argument for number of games. Terminate on invalid input.
        if(args.length == 1 && Integer.parseInt(args[0]) > 0) numberOfGames = Integer.parseInt(args[0]);
        else{
            System.out.println("Enter a positive integer to the command line!");
            System.exit(0);
        }

        game(numberOfGames);


        // Flex upon plebians.
    }
    
}
