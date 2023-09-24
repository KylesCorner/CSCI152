/*
 * Kyle Krstulich
 * 9/6/23
 * BandMatrix.java
 * 
 * Band matrices. Write a program BandMatrix.java that takes two integer command-line arguments n and width and prints an n-by-n pattern like the ones below, with a zero (0) for each element whose distance from the main diagonal is strictly more than width, and an asterisk (*) for each entry that is not, and two spaces between each 0 or *.
 */
public class BandMatrix {

    private static void printMatrix(int[][] matrix){

        for(int x = 0; x < matrix.length; x++){

            for(int y = 0; y < matrix[x].length; y++){

                if(matrix[x][y] > 0){
                    System.out.print("* ");
                }else{
                    System.out.print(matrix[x][y] + " ");                
                }

            }

            System.out.println();
        }
    }
    public static void main(String[] args) {
        
        int n = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);
        int[][] matrix = new int[n][n];


        for(int x = 0; x < matrix.length; x++){

            for(int y = 0; y < matrix[x].length; y++){


                if((x <= (width + y))  && (y <= (width + x))){
                    matrix[x][y] = 1;
                }


            }
        }

        printMatrix(matrix);




    }
    
}
