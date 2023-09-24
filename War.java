/*
 * Kyle Krstulich
 * 9/13/23
 * War.java
 * 
 * Write a program that simulates the card game of war between two players i.e. each player should start out with 26 cards, and each turn the program should generate a random number between 1 and 13 inclusive (which will simulate the card they turn over).  
 * The player with the highest card wins.  In the case of a tie, to determine the winner generate 3 random numbers, and sum them up (player with highest sum wins the turn).  
 * Your program should execute until one player has 0 cards remaining.  Print out the winner, and how many turns it took them to win (use a while or do while loop as well as a switch statement in your solution).
 * 
 * Fix this shit...
 */

public class War {


    private static int[] draw_card(int[] player){

        if(player[0] > 0){
            player[0]--;
            player[1] = (int)Math.floor(Math.random() * 13) + 1;
        }else{
            player[1] = 0;
        }

        return player;
    }

    private static int[] battlefield(){
        int[] answer = new int[2] ;

        for(int i = 0; i< 3; i++){
            answer[0] += (int)Math.floor(Math.random() * 13) + 1;
            answer[1] += (int)Math.floor(Math.random() * 13) + 1;
        }

        return answer;
    }



    
    public static void main(String[] args) {

        //{amount of cards, current card, ammount of wins}
        int[] player1 ={26,0,0};
        int[] player2 ={26,0,0};

        boolean playing = true;

        int turns = 0;

        while(playing){

            turns++;

            player1 = draw_card(player1);
            player2 = draw_card(player2);


            switch(player1[0]){
                case 0:
                playing = false; 
            }
            switch(player2[0]){
                case 0:
                playing = false; 
            }

            if(player1[1] == player2[1]){
                int[] battle = battlefield();
                while(battle[0] == battle[1]){
                    battle = battlefield();
                }
                if(battle[0] > battle[1]){
                    player1[2]++;
                }else{
                    player2[2]++;
                }
            }

            if(player1[1] > player2[1]){
                player1[2]++;
            }else{
                player2[2]++;
            }

        }
        if(player1[2] > player2[2]){
            System.out.print("Player 1 wins with a score of " + player1[2]);
        }
        if(player1[2] < player2[2]){
            System.out.print("Player 2 wins with a score of " + player2[2]);
        }
        if(player1[2] == player2[2]){
            System.out.print("It was a tie with the score of " + player1[2]);
        }

        System.out.println(". It took " + turns + " turns.\n");




    }
}
