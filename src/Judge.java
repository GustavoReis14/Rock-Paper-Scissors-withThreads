public class Judge implements Runnable{
    private Game game;

    public Judge(Game game) {
        this.game = game;
    }


    @Override
    public void run() {

        while (this.game.getBestOf() > 0){
            if (this.game.pointsPlayer1 >= 2 && this.game.pointsPlayer2 != 2
                    || this.game.pointsPlayer2 >= 2 && this.game.pointsPlayer1 != 2) break;
            game.counter();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.checkRoundWinner();
            game.roundsLeft();
        }
        this.game.checkGameWinner();

    }
}
