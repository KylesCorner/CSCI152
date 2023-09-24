/*
 * Kyle Krstulich
 * 9/11/23
 * GraphicSquare.java
 * 
 * Write a program that accepts a single command line argument (the side length of a square) and prints out a graphical representation of the square as well as the area. Please use Math.pow to calculate the area.
 */
public class Square{


    private static void horizontal_line(int side_length){
        for (int i = 1; i <= side_length; i++){
            System.out.print("* ");
        }
        System.out.println();
    }

    private static void vertical_lines(int side_length){

        for (int i = 1; i <= (side_length - 2); i++){
            System.out.print("*");

            for (int j = 1; j <= (side_length - 2); j++){
                System.out.print("  ");
            }

            System.out.println(" *");
        }
    }

    private static void output_square_stats(int side_length){
        double squareArea = Math.pow(side_length,2);
        double squareParimeter = side_length * 4;
        System.out.println("Area: " + squareArea 
        + "\nParimeter: " + squareParimeter);
    }
    public static void main(String[] args) {

        int side_length = Integer.parseInt(args[0]);

        horizontal_line(side_length);
        vertical_lines(side_length);
        horizontal_line(side_length);

        output_square_stats(side_length);

    
    }    
}
