/*
 * Kyle Krstulich
 * 9/12/23
 * DiscreteDistribution.java
 * 
 * Discrete distribution. Write a program DiscreteDistribution.java that takes an integer command-line argument m, followed by a sequence of positive integer command-line arguments a1,a2,…,an
, and prints m random indices (separated by whitespace), choosing each index i
 with probability proportional to ai
.
 */
public class DiscreteDistribution {

    // Prints array to standard output.
    private static void print_array(int[] arr){
        int size  = arr.length - 1;

        for(int i = 0; i <= size; i++){

            // New line for every 25 inputs.
            if(i > 0 && i % 25 == 0){
                System.out.println();
            }

            System.out.print(arr[i] + " ");

        }

        System.out.println();
    }
    public static void main(String[] args) {
    
        int sMax;
        int r;
        int n = args.length - 1;
        int m = Integer.parseInt(args[0]);

        int[] answer = new int[m];
        int[] a = new int[n];
        int[] cumulativeSums = new int[n + 1];

        // To generate a random index i with probability proportional to ai
        for(int i = 0; i < n; i++){

            // Define the cumulative sums Si=a1+a2+…+ai and S0=0
            a[i] = Integer.parseInt(args[i + 1]);
            cumulativeSums[i + 1] = cumulativeSums[i] + a[i]; 
            
        }

        sMax = cumulativeSums[cumulativeSums.length - 1];


        for (int i = 0; i < m; i++){

            // Pick a random integer r uniformly between 0 and Sn−1
            r = (int) (Math.random() * sMax);

            // Find the unique index i between 1 and n such that Si−1≤r<Si
            for (int j = 1; j <= cumulativeSums.length; j++){
                if(r >= cumulativeSums[j - 1] && r < cumulativeSums[j]){

                    answer[i] = j;
                    break;
                }
            }
            

        }


        print_array(answer);
   } 
}
