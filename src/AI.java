import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class AI {

    private static int TIME_LIMIT = 5000;
    //private GameState state;
    Node root;
    public AI() {

    }

    Coordinate aiMakeMove(GameState state) {
        root = new Node(state);
        return root.bestAction.c;
    }

    private class Node {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        GameState state;
        Action bestAction;
        boolean isTerminal = false;
        int player = Utils.AI;
        Node(GameState state) {
            this.state = state;
            max();
        }

        Node(GameState state, int player) {
            this.state = state;
            this.player = player;
            if(this.player == Utils.HUMAN) {
                min();
            } else {
                max();
            }
        }

        private void max() {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player);
                if(next.isTerminal) {
                    this.alpha = Engine.calculateScore(next.state);
                } else if(next.beta >= this.alpha) {
                    this.alpha = next.beta;
                }
                if(bestAction == null || next.beta >= this.bestAction.utility) {
                    this.bestAction = new Action(coord, next.beta);
                }
                if(this.alpha < this.beta) break;
            }
        }

        private void min() {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player);
                if(next.isTerminal) {
                    this.beta = Engine.calculateScore(next.state);
                } else if(next.alpha <= this.beta) {
                    this.beta = next.alpha;
                }
                if(bestAction == null || next.alpha <= this.bestAction.utility) {
                    this.bestAction = new Action(coord, next.alpha);
                }
                if(this.alpha > this.beta) break;
            }
        }

    }

    public Coordinate getNextMove(GameState state) {
        return null;
    }



}
