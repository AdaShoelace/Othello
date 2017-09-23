import javax.swing.*;
import java.util.ArrayList;

/**
 * This class acts as a controller, tying the whole game together
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Othello {

    //DEBUG
    private final boolean DEBUG = false;
    private final boolean ALL_POSSIBLE_STATES = false;
    private final boolean ALL_POSSIBLE_STATES_LIST = true;
    private final boolean FIRST_POSSIBLE_STATE = false;

    private boolean isRunning;
    GameState gameState;
    GUI gui;
    AI ai;
    private int currentPlayer;

    public Othello() {
        gameState = new GameState();
        ai = new AI();
        currentPlayer = Utils.HUMAN;
        isRunning = true;
        gui = new GUI("Othello", gameState, this);
        System.out.println(currentPlayer);
    }

    public void localMultiplayerButtonPressed(Coordinate coord) {
        if(Engine.isValidMove(coord, gameState, currentPlayer)) {
            gameState = Engine.updateBoard(gameState, coord, currentPlayer);
            SwingUtilities.invokeLater(() -> {
                gui.draw(gameState);
                //gui.disableButtons();
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
        if(DEBUG) {
            System.out.println("Initial state");
            Utils.printState(gameState);
            System.out.println("Possible moves");
            //Utils.print(Engine.getValidMoves(gameState, currentPlayer));

            if(ALL_POSSIBLE_STATES) {
                ArrayList<GameState> asd = Engine.generatePossibleStates(gameState, currentPlayer);
                int i = 1;
                for(GameState gs : asd) {
                    System.out.println("Possible state: " + i);
                    Utils.printState(gs);
                    i++;
                }
            } else if(FIRST_POSSIBLE_STATE) {
                    System.out.println("First possible state");
                    gameState = Engine.getFirstPossibleState(gameState, currentPlayer);
                    Utils.printState(gameState);
                    System.out.println("Next possible state");
                    gameState = Engine.getFirstPossibleState(gameState, currentPlayer);
                    Utils.printState(gameState);
            } else if(ALL_POSSIBLE_STATES_LIST) {
                GameState tempState = new GameState();
                /*
                for(Coordinate c : Engine.altGetValidMoves(tempState, currentPlayer)) {
                    System.out.println("Row: " + c.getRow() + "\nCol: " + c.getCol());
                    Utils.printState(Engine.updateBoard(tempState,c,currentPlayer));
                    System.out.println();
                }*/
            }
            return;
        }

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
                gameState = Engine.updateBoard(gameState, ai.aiMakeMove(gameState), currentPlayer);
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

    public static void main(String[] args) {
        new Othello();

    }

}
