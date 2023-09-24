/*
 * Kyle Krstulich
 * 9/11/23
 * DiceRolls.java
 * 
 * Write a program that simulates a dice roll for two players (2 dice), sums the dice for each player and compares them (highest sum is the winner). The program should terminate when one player wins a certain number of times n (passes as an argument to the program). The integer n should be greater than 0, if not, the program should print out an error message. Lastly, the program should print out the final score of each player before terminating. Please use random to simulate a dice roll between 1 and 6 inclusive for this program.
 */
public class Dice{

    private static void print_results(int[] wins){
        int winner;
        if (wins[0] > wins[1]){
            winner = 1;
        }else{
            winner = 2;
        }

        System.out.println("Player " + winner +" wins!");
        System.out.println("Player 1 Score: " + wins[0] + "\nPlayer 2 Score: " + wins[1]);
    }

    private static int roll_dice(){
        int dice = (int)Math.floor(Math.random() * 6) + 1;
        int dice2 = (int)Math.floor(Math.random() * 6) + 1;
        

        return dice + dice2;
    }
   public static void main(String[] args) {

    int winGoal = Integer.parseInt(args[0]);

    boolean isPlaying = winGoal > 0;
    int[] wins = {0,0};


    if (!isPlaying){
        System.out.println("Give me a positive Integer!");
        System.exit(0);
    }

    while (isPlaying){

        int player1 = roll_dice();
        int player2 = roll_dice();


        if(player1 > player2){
            wins[0]++;
        }
        else{
            if(player1 < player2){
                wins[1]++;
            }
        }

        if(wins[0] >= winGoal || wins[1] >= winGoal){
            isPlaying = false;
            print_results(wins);
            break;
        }

    }

    
   } 
}
