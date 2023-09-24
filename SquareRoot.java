/*
 * Kyle Krstulich
 * 9/1/23
 * SquareRoot.java
 * 
 * Write a program that inputs an integer a, and prints out the square root of a to the console.
 */
public class SquareRoot {
    public static void main(String[] args) {
        double num = Math.sqrt(Double.parseDouble(args[0]));
        System.out.println(num);

    }
}
