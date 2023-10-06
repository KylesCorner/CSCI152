/*
 * Kyle Krstulich
 * 10/6/23
 * ArrayHelper.java
 * 
 * 
    Please implement the library ArrayHelper.java (below), and utilize a client to test that your functions are working...

    indexOf - returns the first occurrence of the integer i in myArray.  If the value is not found in myArray then the function should return -1.

    concat - returns a single integer array which contains values in baseArray followed by values in arrayToConcatenate.



    avg - returns the average value in myArray.



    max - returns the max value in myArray.



    min - returns the min value in myArray.



    shuffle - shuffles the values in myArray in a random order.



    print - prints the values of myArray to StdOut on a single line separated by tab (i.e., use escape sequences).



    initialize - returns an integer array of length n initialized with initialValue.



    flatten - returns a single dimensional array with values of my2DArray within it (make sure this works for ragged arrays*)




    public class ArrayHelper
 */
public class ArrayHelper {

    /*
     * TODO: provide documentation
     */
    public static int avg(int[] arrayInput){
        int size = arrayInput.length;
        int averageValue = 0;

        for(int i = 0; i< size; i++){
            averageValue+= arrayInput[i];
        }
        return averageValue/size;
    }
    
    /*
     * TODO: provide documentation
     */
    public static int max(int[] arrayInput){
        int size = arrayInput.length;
        int maxValue = 0;
        int currentValue = 0;

        for(int i = 0; i < size; i++){
            currentValue = arrayInput[i];

            if (currentValue > maxValue){
                maxValue = currentValue;
            }
            
        }

        return maxValue;
    }

    /*
     * TODO: provide documentation
     */
    public static int min(int[] arrayInput){
        int size = arrayInput.length;
        int minValue= 0;
        int currentValue = 0;

        for(int i = 0; i < size; i++){
            currentValue = arrayInput[i];

            if (currentValue < minValue){
                minValue = currentValue;
            }
        }
        return minValue;
    }

    /*
     * TODO: provide documentation
     */
    public static int[] shuffle(int[] arrayInput){
        int randomIndex,temp;
        int size = arrayInput.length;

        for(int i = size; i >= 0; i--){
            randomIndex = (int) (Math.random() * i) + 1;
            temp = arrayInput[randomIndex];
            arrayInput[randomIndex] = arrayInput[i];
            arrayInput[i] = temp; 
        }

        return arrayInput;
    }

    /*
     * TODO: provide documentation
     */
    public static void print(int[] arrayInput){
        int size = arrayInput.length;

        for(int i = 0; i < size; i++){
            System.out.print(arrayInput[i] + " ");
        }
        System.out.println();
    }

    /*
     * TODO: provide documentation
     */
    public static int[] init(int length, int initialValue){
        int[] outputArray = new int[length];
        int size = length;
        for(int i = 0; i < size; i++){
            outputArray[i] = initialValue;
        }
        return outputArray;
    }

    /*
     * TODO: finish method and provide documentation
     */
    public static int[] flatten(int[][] arrayInput){
        return new int[0];
    }


    public static void main(String[] args) {
        
    }
    
}
