public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        new Thread(new Judge(game), "Judge").start();

        new Thread(new PlayGame(game), "Player1").start();
        new Thread(new PlayGame(game), "Player2").start();
    }
}