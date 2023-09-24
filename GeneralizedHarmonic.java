/*
 * Kyle Krstulich
 * 9/6/23
 * GeneralizedHarmonic.java
 * 
 * Generalized harmonic numbers. Write a program GeneralizedHarmonic.java that takes two integer command-line arguments n and r and uses a for loop to compute the nth generalized harmonic number of order r, which is defined by the following formula:
 * H(n,r) = 1/(1^r) + 1/(2^r) + ... + 1/(n^r)
 */
public class GeneralizedHarmonic {

    public static double Harmonic(int n, int r){
        double answer = 0;
        for(int i = 1; i <= n; i++){
            System.out.println(i);
            answer = answer + 1/(Math.pow(i, r)) ;
        }
        return answer;
    }
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);

        System.out.println(Harmonic(n,r));
        
    }
    
}
