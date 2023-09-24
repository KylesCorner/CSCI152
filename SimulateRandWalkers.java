/*
 * Kyle Krstulich
 * 9/6/23
 * SimulateRandWalkers.java
 * 
 * Random walkers. Write a program RandomWalkers.java that takes two integer command-line arguments r and trials. In each of trials independent experiments, simulate a random walk until the random walker is at Manhattan distance r from the starting point. Print the average number of steps.
 */


public class SimulateRandWalkers {
    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        int num_trials = Integer.parseInt(args[1]);
        int steps = 0;

        for(int i = 0; i < num_trials; i++){
            steps = steps + RandomWalker.walk(r, false);
        }

        double avg_trials = (double)steps / (double)num_trials;
        
        System.out.println("Average number of steps = " + avg_trials);
    }
}