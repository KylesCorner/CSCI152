public class WarGame {

    public static void main(String[] args) {
        //TODO cmd line animation time
        int animationTime = Integer.parseInt(args[0]);
        EvenBetterWarLib lib = new EvenBetterWarLib(animationTime);
        String winner;

        while(true){
            if(EvenBetterWarLib.playerDeck.lose()){
                winner = "CPU";
                EvenBetterWarLib.playerCard = 0;
                EvenBetterWarLib.cpuCard = 0;
                break;
            }
            if(EvenBetterWarLib.cpuDeck.lose()){
                winner = "Player";
                EvenBetterWarLib.playerCard = 0;
                EvenBetterWarLib.cpuCard = 0;
                break;
            }

            EvenBetterWarLib.new_cards();

            EvenBetterWarLib.compare_cards();

            EvenBetterWarLib.draw_game();
        }
        EvenBetterWarLib.draw_game();
        EvenBetterWarLib.draw_winner(winner);
    }
}
