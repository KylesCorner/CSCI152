/*
 * Kyle Krstulich
 * 9/1/23
 * AccendingOrder.java
 * 
 * Write a program that takes three integer command-line arguments and prints them in ascending order. Use Math.min() and Math.max().
 */

public class AccendingOrder {
    
    private static void output_array(int[] arr){
        // outputs array to command-line.
        
        System.out.print("Answer: ");
        
        for(int i = 0; i < arr.length; i++){
        
            System.out.print(arr[i] + " ");
        
        }
        
        System.out.println();
    }

    private static int[] convert_array(String[] arr){
        // converts an array of strings to an array of integers.
        int size = arr.length;

        int[] output = new int[size];

        for(int i = 0; i < size; i++){

            output[i] = Integer.parseInt(arr[i]);
        
        }

        return output;
    }
    public static void main(String[] args) {

        int[] nums = convert_array(args);
        int size = nums.length;
        int MinValue;
        int MaxValue;

        // Bubble sort!
        for (int x = 0; x < size - 1; x++){

            for (int y = 0; y < size - x - 1; y++){
           
                MinValue = Math.max(nums[y],nums[y+1]);
                MaxValue = Math.min(nums[y], nums[y+1]);
                nums[y] = MaxValue;
                nums[y+1] = MinValue;

            }
        }
            
        

        output_array(nums);

    }
}
