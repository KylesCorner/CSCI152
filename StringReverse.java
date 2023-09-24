/*
 * Kyle Krstulich
 * 9/13/23
 * ReverseString.java
 * 
 * Write a program that accepts a single string argument from the command line, and prints out the reverse of that string (use a for loop in your program).
 */
public class StringReverse {
    
    public static void main(String[] args) {

        int size = args[0].length() - 1;

        for(int i = size; i >= 0; i--){
            System.out.print(args[0].charAt(i));
        }
        
        System.out.println();

    }
}
