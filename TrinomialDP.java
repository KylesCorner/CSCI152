/*
 * Kyle Krstulich
 * 10/15/23
 * TrinomialDP.java
    Write a program TrinomialDP.java that takes two integer command-line arguments n and k and computes the trinomial coefficient T(n,k)
    using dynamic programming. To do so, organize your program according to the following public API:
    public class TrinomialDP {

        // Returns the trinomial coefficient T(n, k).
        public static long trinomial(int n, int k)

        // Takes two integer command-line arguments n and k and prints T(n, k).
        public static void main(String[] args)
    }
    This version should be fast enough to handle larger values of n and k.
*/
import java.util.HashMap;
public class TrinomialDP {
    private static HashMap<Integer,Integer> calculatedValues = new HashMap<Integer,Integer>(); 
    private static long trinomial(int n, int k){
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

