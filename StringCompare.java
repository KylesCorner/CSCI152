/*
 * Kyle Krstulich
 * 9/13/23
 * EqualStrings.java
 * 
 * Write a program that accepts two strings as input, and prints out if the strings are equal or not i.e. "Equal" or "Not Equal". (use the ternary operator in your program as well as a for loop).
 */
public class StringCompare {
    public static void main(String[] args) {

        String answer = "Not Equal";
        int size = (args[0].length() == args[1].length()) ? args[0].length() : 0;

            for(int i = 0; i < size; i++){

                answer = (args[0].charAt(i) == args[1].charAt(i)) ? "Equal" : "Not Equal";

                if(answer == "Not Equal") break;
            }
        
        System.out.println(answer);
    }    
}