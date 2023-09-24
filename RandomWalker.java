/*
 * Kyle Krstulich
 * 9/6/23
 * RandomWalker.java
 * 
 * Random walk. A Java programmer begins walking aimlessly. At each time step, she takes one step in a random direction (either north, east, south, or west), each with probability 25%. She stops once she is at Manhattan distance r from the starting point. How many steps will the random walker take? This process is known as a two-dimensional random walk.
 */

import java.util.Random;

public class RandomWalker {

    private static void print_point(int x, int y){
        System.out.println("(" + x + ", " + y + ")");
    }

    public static int walk(int r, boolean verbose){


        int steps = 1;
        int direction;
        int x = 0;
        int y = 0;
        boolean walking = true;
        Random rand = new Random();

        if(verbose){print_point(0, 0);}

        do {

            direction = rand.nextInt(4);

            switch (direction){
                case 0: y++;
                break;
                case 1: x++;
                break;
                case 2: y--;
                break;
                default: x--;
                break;
            }


            if (verbose){print_point(x,y);}

            if (Math.abs(x) + Math.abs(y) == r){
                walking = false;
            }else{
                steps++;
            }
            
        }
        while(walking);
        return steps;
    }

    public static void main(String[] args) {
    
        int r = Integer.parseInt(args[0]);
        int steps = walk(r,true);


        System.out.println("Steps: " + steps);

 }   
}
