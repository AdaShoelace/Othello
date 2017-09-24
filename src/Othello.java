import javax.swing.*;
import java.util.ArrayList;

/**
 * This class acts as a controller, tying the whole game together
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Othello {
    GameState gameState;
    GUI gui;
    AI ai;
    private int currentPlayer;

    public Othello() {
        gameState = new GameState();
        ai = new AI();
        currentPlayer = Utils.HUMAN;
        gui = new GUI("Othello", gameState, this);
        System.out.println(currentPlayer);
    }

    public void localMultiplayerButtonPressed(Coordinate coord) {
        if(Engine.isValidMove(coord, gameState, currentPlayer)) {
            gameState = Engine.updateBoard(gameState, coord, currentPlayer);
            SwingUtilities.invokeLater(() -> {
                gui.draw(gameState);
            });
            currentPlayer = -currentPlayer;
        } else {
            return;
        }
    }

    /**
     * This is run when ever the human player press a coordinate in the GUI
     * It sets the current player, calls for a redraw of the GUI and trigger the AI's next move
     * @param coord
     */
    public void buttonPressed(Coordinate coord) {

        if(Engine.isValidMove(coord, gameState, currentPlayer)) {
            System.out.println("Is valid move");
            gameState = Engine.updateBoard(gameState, coord, currentPlayer);
            SwingUtilities.invokeLater(() -> {
                gui.draw(gameState);
                gui.disableButtons();
            });
            currentPlayer = Utils.AI;
            do{
                System.out.println("Hejsan från ai");
                gameState = Engine.updateBoard(gameState, ai.aiMakeMove(gameState, this), currentPlayer);
                SwingUtilities.invokeLater(() -> {
                    gui.draw(gameState);
                });
            }
            while(currentPlayer == Utils.AI && !Engine.hasValidMoves(gameState, Utils.HUMAN));

        } else {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            gui.enableButtons();
        });
        currentPlayer = Utils.HUMAN;
    }

    public void setNodeString(long count) {
        gui.setNodes(count);
    }

    public static void main(String[] args) {
        new Othello();

    }

}
