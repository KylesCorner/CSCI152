/*
 * Kyle Krstulich
 * 9/20/23
 * IntoBinary.java
 * 
 * Takes in multiaple command-line arguments to convert to binary.
 */
public class IntoBinary {

    public static String decimal_to_binary(int number){

        String output = "";
        boolean loop_running = true;

        while(loop_running){
            if(number/2 == 0) loop_running = false;
            output += number % 2;
            number /= 2;
        }

        char[] reverse_output = output.toCharArray();
        output = "";
        int size = reverse_output.length - 1;

        for(int i = size; i >= 0; i--){
            output+= reverse_output[i];
        }

        output += "\n";

        return output;

    }

    public static void main(String[] args) {
        int size = args.length;
        String answer = "";

        for(int i = 0; i < size; i++){

            answer+=decimal_to_binary(Integer.parseInt(args[i]));
        }


        System.out.println(answer);

    }

    
}