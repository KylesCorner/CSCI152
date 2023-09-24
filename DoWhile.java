public class DoWhile{
    public static void main(String[] args) {
        int goal = Integer.parseInt(args[0]);
        int power = 0;
        int remander = 0;

        do{
            power++;
        }
        while((int)Math.pow(2, power) <= goal);
        power--;
        remander = goal % (int)Math.pow(2,power);

        System.out.println("2^"+power+" + " + remander + " = " + goal);

        
        
        


    }
}