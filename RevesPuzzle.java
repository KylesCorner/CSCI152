/*
 * Kyle Krstulich
 * 10/9/23
 * RevesPuzzle.java
 * 
 * Write a program RevesPuzzle.java that takes an integer command-line argument n and prints a solution to Reve’s puzzle.
 * Assume that the the discs are labeled in increasing order of size from 1 to n and that the poles are labeled A, B, C, and D, with A representing the starting pole and D representing the destination pole.
 */
public class RevesPuzzle {
    private static final double DISC_MOD = 2.5; 
    private static final int NUMBER_OF_POLES= 3;
    private static final int ANIMATION_TIME = 500;

    private static double[][] poleCoords;
    private static int[][] gameBoard;
    private static int numDisks;



    private static void init(){
        gameBoard = new int[NUMBER_OF_POLES][numDisks];
        poleCoords = new double[NUMBER_OF_POLES][4];
        double yCoord = DISC_MOD*numDisks + DISC_MOD;
        double xCoord = 0;

        StdDraw.setXscale(-10, DISC_MOD*numDisks*NUMBER_OF_POLES*2 + DISC_MOD);
        StdDraw.setYscale(0, yCoord*2);

        for(int i = 0; i < NUMBER_OF_POLES; i++){
            xCoord = DISC_MOD*numDisks * (i * DISC_MOD) + DISC_MOD;
            double[] arrayTemps = {xCoord, 0 , xCoord, yCoord};
            poleCoords[i] = arrayTemps;
        }

        for(int i = 0; i < numDisks; i++){
            gameBoard[0][i] = numDisks - i;
        }
        draw_poles();


    }

    //TODO fix this so it dynamically scales with as many poles as I want.
    private static void draw_poles(){
        StdDraw.clear();
        for(double[] coord : poleCoords){
            StdDraw.line(coord[0], coord[1], coord[2], coord[3]);
        }
        StdDraw.show();

    }
    
    private static void draw_disks(){
        double widthMod,xCoord,yCoord;
        

        for(int pole = 0; pole < NUMBER_OF_POLES; pole++){

            for(int disc = 0; disc < numDisks; disc++){
                if(gameBoard[pole][disc] > 0){

                    xCoord = poleCoords[pole][0];
                    yCoord = (DISC_MOD) * (disc+1);
                    widthMod = DISC_MOD * gameBoard[pole][disc];
                    System.out.println(widthMod);
                    StdDraw.rectangle(xCoord, yCoord, widthMod, DISC_MOD/2);
                }

            }

        }


        StdDraw.show();



    }

    private static void output_board(){
        StdDraw.pause(ANIMATION_TIME);
        draw_poles();
        draw_disks();
        
    }

    //TODO add algorithms for hanoi and reves puzzle.
    public static void main(String[] args) {
        numDisks  = Integer.parseInt(args[0]);
        init();
        output_board();

        
    }
    
}
