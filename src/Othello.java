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
    private int currentPlayer;

    public Othello() {
        gameState = new GameState();
        engine = new Engine();
        ai = new AI();
        if(JOptionPane.showConfirmDialog(null,
                "Does the human player start?",
                "Choose starting player",
                JOptionPane.YES_NO_OPTION) == 0) {
            currentPlayer = Utils.HUMAN;
        } else {
            currentPlayer = Utils.AI;
        }
        isRunning = true;
        System.out.println(currentPlayer);
    }

    public void run() {
        gui = new GUI("Othello", gameState);
        while(isRunning) {
            update();
        }
    }

    public void update() {
        currentPlayer = currentPlayer == Utils.AI ? Utils.HUMAN : Utils.AI;

        if(currentPlayer == Utils.HUMAN) {
            engine.placeChip(Utils.HUMAN, gui.getLastButtonPressed(), gameState);
        }
        gui.draw(gameState);

    }


    public static void main(String[] args) {
        new Othello().run();

    }

}
