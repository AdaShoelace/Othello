import javax.swing.*;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Othello {

    private boolean isRunning;
    GameState gameState;
    GUI gui;
    Engine engine;
    AI ai;
    Engine.PlayerType currentPlayer;

    public Othello() {
        gameState = new GameState();
        engine = new Engine();
        ai = new AI();
        if(JOptionPane.showConfirmDialog(null,
                "Does the human player start?",
                "Choose starting player",
                JOptionPane.YES_NO_OPTION) == 0) {
            currentPlayer = Engine.PlayerType.HUMAN;
        } else {
            currentPlayer = Engine.PlayerType.AI;
        }
        isRunning = true;
        System.out.println(currentPlayer.toString());
    }

    public void run() {
        gui = new GUI("Othello", gameState);
        while(isRunning) {

        }
    }

    public void update() {
        currentPlayer = currentPlayer == Engine.PlayerType.AI ? Engine.PlayerType.HUMAN : Engine.PlayerType.AI;

    }


    public static void main(String[] args) {
        new Othello().run();

    }

}
