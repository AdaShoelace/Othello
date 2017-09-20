import javax.swing.*;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Othello {

    private boolean isRunning;
    GameState gameState;
    GUI gui;
    AI ai;
    private int currentPlayer;

    public Othello() {
        gameState = new GameState();
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
        gui = new GUI("Othello", gameState, this);
        System.out.println(currentPlayer);
    }


    public void buttonPressed(Coordinate coord) {

        Utils.print(Engine.getValidMoves(gameState, currentPlayer));
        System.out.println("Valid move count: " + Engine.validMoveCount(gameState, currentPlayer));

        if(Engine.isValidMove(coord, gameState, currentPlayer)) {
            gameState = Engine.updateBoard(gameState, coord, currentPlayer);
            SwingUtilities.invokeLater(() -> {
                gui.draw(gameState);
                gui.disableButtons();
            });
            currentPlayer = Utils.AI;
        }

        while(Engine.hasValidMoves(gameState, Utils.AI) /*&& !Engine.hasValidMoves(gameState, Utils.HUMAN)*/) {
            currentPlayer = Utils.HUMAN;
            SwingUtilities.invokeLater(() -> {
                gui.draw(gameState);
                gui.enableButtons();
            });
            //This break is here only for debugging purposes
            break;
        }
    }


    public static void main(String[] args) {
        new Othello();

    }

}
