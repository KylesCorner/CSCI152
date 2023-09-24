/*
 * Kyle Krstulich
 * 9/12/23
 * ThueMorse.java
 * 
 * Thue–Morse weave. Write a program ThueMorse.java that takes an integer command-line argument n and prints an n-by-n pattern like the ones below. Include two space characters between each + and - character.
 */


public class ThueMorse {

    //Concatanates 2 arrays of the same length.
    private static int[] concatnate_array(int[] arr1, int[] arr2){

        int size = arr1.length;
        int[] outputArray = new int[size*2];


        System.arraycopy(arr1, 0, outputArray, 0, size);
        System.arraycopy(arr2, 0, outputArray, size, size);


        return outputArray;

    }

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

    // Outputs array to standard output. 25 items per line.
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
