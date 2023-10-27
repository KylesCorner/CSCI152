public class WarTime {

    private static double log2(double x){
        double answer = Math.log(x) / Math.log(2);

        return answer;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int numberOfGames = 1;
        double[][] times = new double[2][n];

        for(int i = 0; i < n; i++){

           Stopwatch time = new Stopwatch();
           BetterWar.game_no_IO(numberOfGames);
           times[0][i] = Double.valueOf(numberOfGames);
           times[1][i] = time.elapsedTime();

           numberOfGames*=2;

        }

        ArrayHelper.print(times);

        
    }
    
}
