/*
 * Kyle Krstulich
 * 8/28/23
 * CSCI151
 * RightTriangle.java
 */
public class RightTriangle{


    public static void main(String[] args) {

        int A = Integer.parseInt(args[0]);
        int B = Integer.parseInt(args[1]);
        int C = Integer.parseInt(args[2]);


        double answer = Math.pow(A,2) + Math.pow(B,2);



        System.out.println(answer == Math.pow(C,2));
    }
}