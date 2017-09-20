/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class GameState {

    private int gameGrid[][];
    private int playerScore = 0;
    private int aiScore = 0;

    public GameState() {
        gameGrid = createGrid();
    }

    /**
     * Copy constructor
     * @param state
     */
    public GameState(GameState state) {
        gameGrid = state.gameGrid;
    }

    /**
     * Sets the given cell in the board
     * @param player
     * @param coord
     */
    public void setCell(int player, Coordinate coord) {
        gameGrid[coord.getRow()][coord.getCol()] = player == Utils.HUMAN ? Utils.HUMAN
                : Utils.AI;
    }

    /**
     * Creates the initial game state with the four chips in the middle
     * @return
     */
    private int[][] createGrid() {
        int[][] ret = new int[Utils.ROWS][Utils.COLS];
        for(int r = 0; r < Utils.ROWS; ++r) {
            for(int c = 0; c < Utils.COLS; ++c) {
                ret[r][c] = Utils.NONE;
            }
        }
        ret[Utils.ROWS/2 - 1][Utils.COLS/2 - 1] = Utils.AI;
        ret[Utils.ROWS/2 - 1][Utils.COLS/2] = Utils.HUMAN;
        ret[Utils.ROWS/2][Utils.COLS/2 - 1] = Utils.HUMAN;
        ret[Utils.ROWS/2][Utils.COLS/2] = Utils.AI;
        return ret;
    }

    public int[][] getGrid() {
        return gameGrid;
    }

}
