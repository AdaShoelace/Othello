import java.util.ArrayList;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class AI {
    //private GameState state;
    Node n;
    public AI() {

    }

    void aiMakeMove(GameState state) {
        n = new Node(state);
        n.addChildren(n);
    }

    private class Node {
        int alpha = Integer.MAX_VALUE;
        int beta = Integer.MIN_VALUE;
        boolean isTerminal = false;
        int player = Utils.HUMAN;
        GameState state;
        ArrayList<Node> children;
        Node(GameState state) {
            this.state = state;
            children = new ArrayList<>();
        }

        //TODO this function isn't working correclty.
        //TODO it is supposed to add nodes containing new states each based on
        //TODO each separate calid coordinate from the grid of possible moves of parent node
        void addChildren(Node n) {
            boolean[][] validMoves = Engine.getValidMoves(n.state,this.player);
            System.out.println("Created from: ");
            Utils.print(validMoves);
            for(int i = 0; i < validMoves.length; i++) {
                for(int j = 0; j < validMoves[0].length; j++) {
                    if(validMoves[i][j]) {
                        children.add(new Node(Engine.updateBoard(new GameState(n.state),
                                new Coordinate(i,j), -player)));
                    }
                }
            }

        }
    }

    void printChildren(Node n) {
        int i = 1;
        Utils.printState(n.state);
        System.out.println();

        for(Node p : n.children) {
            System.out.println("Child: " + i + " ");
            Utils.printState(p.state);
            System.out.println();
            i++;
        }
    }

    public Coordinate getNextMove(GameState state) {
        return null;
    }



}
