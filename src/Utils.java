/**
 * Created by Pierre Lejdbring on 9/14/17.
 */
public class Utils {

    public final static int ROWS = 4;
    public final static int COLS = 4;
    public final static int AI = -1;
    public final static int HUMAN = 1;
    public final static int NONE = 0;

    private Utils() {

    }

    /**
     * Helper function to print the given matrix to the console
     * @param b - boolean matrix containing possible moves
     */
    public static void print(boolean[][] b) {
        for(boolean[] row : b) {
            for(boolean elem: row) {
                System.out.print(elem ? "(" + 1 + ")" : "(" + 0 + ")");
            }
            System.out.println();
        }
    }

    public static void printState(GameState state) {
        int[][] grid = state.getGrid();
        for(int[] i : grid) {
            for(int j : i) {
                System.out.print(j < 0 ? "(" + j +")" : "( " + j + ")");
            }
            System.out.println();
        }
    }
}
