/*
 * Kyle Krstulich
 * 9/12/23
 * ThueMorse.java
 * 
 * Thue–Morse weave. Write a program ThueMorse.java that takes an integer command-line argument n and prints an n-by-n pattern like the ones below. Include two space characters between each + and - character.
 */


public class ThueMorse {

    /*
    Description:
        Concatenates (combines) two integer arrays, arr1 and arr2, into a single integer array, outputArray, where the elements of arr2 follow those of arr1.

    Parameters:

        arr1 (int[]): The first integer array to be concatenated.
        arr2 (int[]): The second integer array to be concatenated.

    Returns:

        int[]: A new integer array containing the concatenated elements from arr1 followed by arr2.

    This method takes two integer arrays, arr1 and arr2, and creates a new integer array, outputArray, of twice the size to accommodate both arrays. It uses the System.arraycopy 
    method to copy the elements from arr1 into the first half of outputArray and the elements from arr2 into the second half of outputArray. The result is a new array containing 
    the elements from both input arrays in sequential order. The original arrays arr1 and arr2 remain unchanged, and the method returns the concatenated outputArray.
     */
    private static int[] concatnate_array(int[] arr1, int[] arr2){

        int size = arr1.length;
        int[] outputArray = new int[size*2];


        System.arraycopy(arr1, 0, outputArray, 0, size);
        System.arraycopy(arr2, 0, outputArray, size, size);


        return outputArray;

    }

    /*
    Description:
        Performs a bitwise negation (complement) operation on each element of an integer array, producing a new array with complementary values (1s converted to 0s and vice versa).

    Parameters:

        arr (int[]): The integer array on which the bitwise negation operation will be applied.

    Returns:

        int[]: A new integer array containing complementary values to the original array.

    This method takes an integer array arr and produces a new array where each element is the result of a bitwise negation operation applied to the corresponding element in the original array. 
    If an element in the original array is 1, it becomes 0 in the new array, and if an element is 0, it becomes 1. The original array remains unchanged, and the method returns the new array with complementary values.
     */
    private static int[] bitwise_negate_over_array(int[] arr){
        
        int size = arr.length;
        int[] output = new int[size];

        for(int i = 0; i < size; i++){

            if(arr[i] == 1){
                output[i] = 0;
            }else{
                output[i] = 1;
            }

        }


        return output;
    }

    /*
    Description:

        Outputs the elements of an integer array to the command line, formatting them with line breaks to create rows of elements. The method prints each element of the array in sequence, breaking lines after every 8 elements.

    Parameters:

        arr (int[]): The integer array to be displayed on the command line.

    Returns:

        None

    This method takes an integer array arr and displays its elements on the command line. It prints each element sequentially and inserts a line break after every 8 elements to format the output as rows. The method does not modify 
    the array and is solely responsible for printing its contents in a readable format.
     */
    private static void output_array(int[] arr){
        int size = arr.length - 1;

        for(int i = 0; i <= size; i++){
            
            if(i > 0 && i % 8 == 0){
                System.out.println();
            }

            System.out.print(arr[i]);
        }

        System.out.println();
    }

    /*
    Description:
        Outputs an integer array as a "weave" pattern, where the elements of the array are displayed in rows of "n" elements each, with alternating symbols ('-' and '+') representing different values in the array.

    Parameters:

        arr (int[]): The integer array to be displayed as a weave pattern.
        n (int): The number of elements per row in the weave pattern.

    Returns:
        None

    This method takes an integer array arr and displays it as a weave pattern with rows of "n" elements each. In the pattern, 1 is represented by a '-' symbol, and 0 is represented by a '+' symbol. The method iterates 
    through the array and prints each element accordingly, creating a weave pattern effect. It inserts a new line after each row of "n" elements to format the output.
     */
    private static void output_array_as_weave(int[] arr, int n){

        int size = arr.length;

        for (int i = 0; i < size; i++){

            if(i > 0 && i % n == 0){
                System.out.println();
            }


            if(arr[i] == 1) System.out.print("-  ");

            if(arr[i] == 0) System.out.print("+  ");
        }

        System.out.println();
    }
    
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int[] answer = {0};
        int stopping_point = n*n - 1;
    
        while(stopping_point >= answer.length){
            int[] temp = bitwise_negate_over_array(answer);
            answer = concatnate_array(answer, temp);
        }



        output_array_as_weave(answer, n);


        



        
    }
}
