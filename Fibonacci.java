/*
 * Kyle Krstulich
 * 10/16/23
 * Fibonacci.java
 * 
 * fibonacci sequence in java
 */

public class Fibonacci {

    private static long[] answers;


    public static long fib(int n){

        if(n<=1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        if(answers[n] == 0){
            answers[n] = fib(n-1) + fib(n-2);
        }

        return answers[n];

    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        answers = new long[n+1];
        System.out.println(fib(n));

        
    }
    
}
