public class SimpleSort{
    static double[] times;
    static final int AMOUNT_OF_DOUBLES = 10;

    public static void printArray(int[] myArray){
        for (int i = 0; i < myArray.length; i++){
            StdOut.printf("%d\t", myArray[i]);
        }
        StdOut.println();
    }
    public static void printArray(String[] myArray){
        for (int i = 0; i < myArray.length; i++){
            StdOut.printf("%10s\t", myArray[i]);
        }
        StdOut.println();
    }
    public static void printArray(double[] myArray){
        for (int i = 0; i < myArray.length; i++){
            StdOut.printf("%f\t", myArray[i]);
        }
        StdOut.println();
    }
    

    public static void printTable(String[] title, int[] n, double[] t, double[] t2Overt, double[] logt2Overt){

        printArray(title);
        for(int i = 0; i < AMOUNT_OF_DOUBLES-2; i++){
            
            StdOut.printf("%10d\t%10.4f\t%10.4f\t%10.4f\n", n[i], t[i], t2Overt[i], logt2Overt[i]);
        }
        StdOut.printf("%10d\t%10.4f\t%10.4f\t%10.4f\n",n[AMOUNT_OF_DOUBLES-1],
                                                    t[AMOUNT_OF_DOUBLES-1],
                                                    0.0,
                                                    0.0);


        
    }

    // Calculates lg(n).
    public static double log2(double N)
    {

        // calculate log2 N indirectly
        // using log() method
        double result = Math.log(N) / Math.log(2);

        return result;
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] nArray = new int[AMOUNT_OF_DOUBLES];
        double[] times = new double[AMOUNT_OF_DOUBLES];
        double[] doubleTimes = new double[AMOUNT_OF_DOUBLES-1];
        double[] logDoubleTimes = new double[AMOUNT_OF_DOUBLES-1];
        String[] title = {
            "n",
            "T",
            "2T/T",
            "Log(2T/T)"
        };


        
        for(int i = 0; i < AMOUNT_OF_DOUBLES; i++){

            nArray[i] = n;
            times[i] = timeTrial(n);
            n += n;
        }

        for(int i = 0; i <AMOUNT_OF_DOUBLES-1; i++){
            doubleTimes[i] = times[i+1] / times[i];
            logDoubleTimes[i] = log2(doubleTimes[i]);
        }

        printTable(title, nArray, times, doubleTimes, logDoubleTimes);

        
    }

    public static double timeTrial(int n){
        int[] randomArray = new int[n];
    
        for (int i = 0; i < n; i++){
            randomArray[i] = StdRandom.uniformInt(-1000, 1001);
        }
        Stopwatch watch = new Stopwatch();
        simpleSort(randomArray);

        return watch.elapsedTime();
    }
    public static void simpleSort(int[] myArray){
        int temp;
        for(int i = 0; i < myArray.length-1; i++){
            for(int j = i + 1; j < myArray.length; j++){
                if (myArray[i] > myArray[j]){
                    temp = myArray[i];
                    myArray[i] = myArray[j];
                    myArray[j] = temp;
                }
            }
        }
    }
}