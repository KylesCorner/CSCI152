/*
 * Kyle Krstulich
 * 9/1/23
 * ItemSwap.java
 * 
 * Write a program that inputs integers a and b (from command line) swaps their order by doing an assignment swap i.e. a = b, and b = a, etc.. then prints out the swapped order to the console.
 */

public class ItemSwap {
    
    public static void main(String[] args) {
        int A = Integer.parseInt(args[0]);
        int B = Integer.parseInt(args[1]);
        int temp;
        
        temp = A;
        A = B;
        B = temp;



        System.out.println("A: " + A + "\nB: " + B);
    }
}
