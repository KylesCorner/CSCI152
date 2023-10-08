public class ArrayClient {
   public static final int NUMBER_OF_TESTS = 30; 
   private static final String LINE = "-------------------------------------------";
   public static void main(String[] args) {

      // ragged array testing variables
      int[][] raggedArrayActule = new int[NUMBER_OF_TESTS][];
      int[][] raggedArrayShuffled, raggedArrayConcat;
      int[] raggedArrayTurnedFlat, indexValues;
      int[] arrayToConcat = {11,12,13};
      int raggedArrayActuleSize = 0;
      int raggedMax,raggedMin,raggedAvg;
      
     //Creation of ragged array 
      for(int i = 1; i <= NUMBER_OF_TESTS; i++){
         raggedArrayActule[i-1] = ArrayHelper.init(i+1, 'c');
         raggedArrayActuleSize += i+1;

      }

      //testing ragged arrays
      StdOut.println(LINE);
      StdOut.println("Testing with ragged arrays");
      StdOut.println(LINE);

      StdOut.println("Flatten test with ragged array");
      StdOut.println(LINE);
      ArrayHelper.print(raggedArrayActule);
      raggedArrayTurnedFlat = ArrayHelper.flatten(raggedArrayActule);
      StdOut.println(LINE);
      StdOut.println("Length Test: " + (raggedArrayTurnedFlat.length == raggedArrayActuleSize ? "Pass": "Fail"));
      ArrayHelper.print(raggedArrayTurnedFlat);
      StdOut.println(LINE);
      
      //testing shuffle
      StdOut.println("Ragged array shuffle test");
      StdOut.println(LINE);
      ArrayHelper.shuffle(raggedArrayActule);
      ArrayHelper.print(raggedArrayActule);
      StdOut.println(LINE);


      //testing min
      StdOut.println("Testing min on ragged array");
      raggedMin = ArrayHelper.min(raggedArrayActule);
      StdOut.println(LINE);
      StdOut.println("Min resuts: " + (0 == raggedMin ? "Passed" : "Failed"));
      StdOut.println(LINE);

      //testing max
      StdOut.println("Testing max on ragged array");
      raggedMax = ArrayHelper.max(raggedArrayActule);
      StdOut.println(LINE);
      StdOut.println("Max resuts: " + (NUMBER_OF_TESTS == raggedMax ? "Passed" : "Failed"));
      StdOut.println(LINE);
      
      //testing avg 
      StdOut.println("Testing avg on ragged array");
      StdOut.println(LINE);
      StdOut.println("Avg value: " + ArrayHelper.avg(raggedArrayActule));
      StdOut.println(LINE);

      //testing concat
      StdOut.println("Testing concat on ragged array");
      raggedArrayConcat = ArrayHelper.concat(raggedArrayActule, arrayToConcat);
      StdOut.println(LINE);
      ArrayHelper.print(raggedArrayConcat);
      StdOut.println(LINE);
      StdOut.println("Length Test: " + (ArrayHelper.flatten(raggedArrayConcat).length == (raggedArrayActuleSize + arrayToConcat.length) ? "Pass": "Fail"));
      StdOut.println(LINE);

      //testing indexOf 
      StdOut.println("Testing indexOf on ragged array");
      indexValues = ArrayHelper.indexOf(4, raggedArrayConcat);
      StdOut.println(LINE);
      if(indexValues[1] >= 0){
         StdOut.println("IndexOf Test: " + (4 == raggedArrayConcat[indexValues[0]][indexValues[1]] ? "Passed" : "Failed"));
      }else{
         StdOut.println("IndexOf Test: Failed to find value");
      }

      StdOut.println(LINE);
   }
}
