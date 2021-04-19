public class PlayGame implements Runnable {
    Game game;

    public PlayGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (this.game.getBestOf() > 0) {
            this.game.play();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
