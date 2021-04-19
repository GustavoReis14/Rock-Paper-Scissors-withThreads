import javax.sound.midi.SysexMessage;
import java.util.Random;

public class Game {
    private int bestOf;
    private String player1;
    private String player2;
    public int pointsPlayer1;
    public int pointsPlayer2;
    public boolean ready;

    public Game(){
        this.ready = false;
        this.bestOf = 3;
        this.pointsPlayer1 = 0;
        this.pointsPlayer2 = 0;
    }

    public synchronized int getBestOf() {
        return bestOf;
    }

    public void roundsLeft() {
        this.bestOf--;
    }

    private synchronized String choose(){
        Random random = new Random();
        int toCheck = random.nextInt(3);
        switch (toCheck) {
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            case 2:
                return "Seasor";
        }
        return "";
    }

    public void play() {
        synchronized (this){
            if (!this.ready){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
        if (getBestOf() > 0) {
            if(Thread.currentThread().getName() == "Player1") {
                this.player1 = choose();
                System.out.println("Player 1 = "+ this.player1);
            } else {
                this.player2 = choose();
                System.out.println("Player 2 = "+ this.player2);

            }
        }
        this.ready = false;
    }

    public synchronized void checkRoundWinner() {
        if (this.player1 == "Rock" && this.player2 == "Rock"){
            System.out.println("DRAW");
            this.pointsPlayer2++;
            this.pointsPlayer1++;
        }else if (this.player1 == "Rock" && this.player2 == "Paper"){
            System.out.println("PLAYER 2 WON");
            this.pointsPlayer2++;
        }else if (this.player1 == "Rock" && this.player2 == "Seasor") {
            System.out.println("PLAYER 1 WON");
            this.pointsPlayer1++;
        }else if (this.player1 == "Paper" && this.player2 == "Paper"){
            System.out.println("DRAW");
            this.pointsPlayer2++;
            this.pointsPlayer1++;
        }else if (this.player1 == "Paper" && this.player2 == "Seasor"){
            System.out.println("PLAYER 2 WON");
            this.pointsPlayer2++;
        }else if (this.player1 == "Paper" && this.player2 == "Rock") {
            System.out.println("PLAYER 1 WON");
            this.pointsPlayer1++;
        }else if (this.player1 == "Seasor" && this.player2 == "Seasor"){
            System.out.println("DRAW");
            this.pointsPlayer2++;
            this.pointsPlayer1++;
        }else if (this.player1 == "Seasor" && this.player2 == "Rock"){
            System.out.println("PLAYER 2 WON");
            this.pointsPlayer2++;
        }else if (this.player1 == "Seasor" && this.player2 == "Paper") {
            System.out.println("PLAYER 1 WON");
            this.pointsPlayer1++;
        }
    }

    public synchronized void checkGameWinner() {
        this.bestOf = 0;
        if (this.pointsPlayer1 > this.pointsPlayer2) System.out.println("PLAYER 1 WON THE MATCH!");
        else if (this.pointsPlayer2 > this.pointsPlayer1) System.out.println("PLAYER 2 WON THE MATCH!");
        else System.out.println("DRAWWW!!");
        this.notifyAll();
    }

    public synchronized void counter() {
        System.out.println("\nPREPARE");
        System.out.println("JO");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("KEN");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("PÔ!\n");
        this.ready = true;
        this.notifyAll();
    }
}
