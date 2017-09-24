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

    Coordinate aiMakeMove(GameState state, Othello controller) {
        root = new Node(state, 0, controller);
        return root.bestAction.c;
    }

    private class Node {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        GameState state;
        Action bestAction;
        boolean isTerminal = false;
        int player = Utils.AI;
        Othello controller;
        Node(GameState state, long count, Othello controller) {
            this.state = state;
            this.controller = controller;
            controller.setNodeString(count);
            max(count, controller);
        }

        Node(GameState state, int player, int alpha, int beta, long count, Othello controller) {
            this.state = state;
            this.player = player;
            this.alpha = alpha;
            this.beta = beta;
            this.controller = controller;
            controller.setNodeString(count);
            if(this.player == Utils.HUMAN) {
                min(count, controller);
            } else {

                max(count, controller);
            }
        }

        private void max(long count, Othello controller) {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player, this.alpha, this.beta, count + 1, controller);
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

        private void min(long count, Othello controller) {
            ArrayList<Coordinate> validMoves = Engine.getValidMoves(this.state, this.player);
            if(validMoves.size() == 0) this.isTerminal = true;
            for(Coordinate coord : validMoves) {
                Node next = new Node(Engine.updateBoard(this.state, coord, this.player), -this.player, this.alpha, this.beta, count + 1, controller);
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
