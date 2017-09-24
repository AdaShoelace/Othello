import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class AI {

    Node root;
    public AI() {

    }

    /**
     * Builds a minimax tree and returns the best move for AI
     * @param state
     * @param controller
     * @return
     */
    Coordinate aiMakeMove(GameState state, Othello controller) {
        //Time for when the tree construction is initiated, later used for evaluating cutoff
        long timeStarted = System.currentTimeMillis();
        root = new Node(state, 0, controller, timeStarted);
        return root.bestAction.c;
    }

    /**
     * Class that acts as the model for the treenodes.
     * Recursive constructors in which the alpha-beta-search is implemented
     */
    private class Node {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        GameState state;
        Action bestAction;
        boolean isTerminal = false;
        int player = Utils.AI;
        Othello controller;
        Node(GameState state, long count, Othello controller, long startTime) {
            this.state = state;
            this.controller = controller;
            controller.setNodeString(count);
            max(count, controller, startTime + Utils.TIME);
        }

        private Node(GameState state, int player, int alpha, int beta, long count, Othello controller, long cutoff) {
            this.state = state;
            this.player = player;
            this.alpha = alpha;
            this.beta = beta;
            this.controller = controller;
            controller.setNodeString(count);
            if(this.player == Utils.HUMAN) {
                min(count, controller, cutoff);
            } else {

                max(count, controller, cutoff);
            }
        }

        private void max(long count, Othello controller, long cutoff) {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0 || System.currentTimeMillis() > cutoff) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player, this.alpha, this.beta, count + 1, controller, cutoff);
                if(next.isTerminal) {
                    this.alpha = Engine.calculateScore(next.state);
                } else if(next.beta >= this.alpha) {
                    this.alpha = next.beta;
                }
                if(bestAction == null || next.beta >= this.bestAction.utility) {
                    this.bestAction = new Action(coord, next.beta);
                }
                if(this.alpha <= this.beta) break;
            }
        }

        private void min(long count, Othello controller, long cutoff) {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0 || System.currentTimeMillis() > cutoff) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player, this.alpha, this.beta, count + 1, controller, cutoff);
                if(next.isTerminal) {
                    this.beta = Engine.calculateScore(next.state);
                } else if(next.alpha <= this.beta) {
                    this.beta = next.alpha;
                }
                if(bestAction == null || next.alpha <= this.bestAction.utility) {
                    this.bestAction = new Action(coord, next.alpha);
                }
                if(this.alpha <= this.beta) break;
            }
        }

    }
}
