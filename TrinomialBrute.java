/*
 * Kyle Krstulich
 * 10/9/23
 * TrinomialBrute
 * 
 * Trinomial coefficients (brute force). Write a program TrinomialBrute.java that takes two integer command-line arguments n and k and computes the corresponding trinomial coefficient. The trinomial coefficient T(n,k)
 is the coefficient of xn+k
 in the expansion of (1+x+x2)n

 */
public class TrinomialBrute {

    private static int trinomial(int n, int k){
        if(n == 0 && k ==0){
            return 1;
        }
        if(k < -n || k > n){
            return 0;
        }

        return trinomial(n-1, k-1) + trinomial(n-1, k) + trinomial(n-1, k+1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        StdOut.println(trinomial(n, k));
        
    }
    
}
