import java.util.Random;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class AI {
    //private GameState state;

    public AI() {

    }

    private class Node {
        int alpha = Integer.MAX_VALUE;
        int beta = Integer.MIN_VALUE;
        boolean isTerminal = false;
        int depth = 0;
        int player = Utils.HUMAN;
        GameState state;
        Node(GameState state) {this.state = state;}
    }

    public Coordinate getNextMove(GameState state) {
        
    }



}
