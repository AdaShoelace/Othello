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

    public GameState(GameState state) {
        gameGrid = state.gameGrid;
    }

    public void setCell(int type, Coordinate coord) {
        gameGrid[coord.getRow()][coord.getCol()] = type == Utils.HUMAN ? Utils.HUMAN
                : Utils.AI;
    }

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

    public int getPlayerScore() {
        return playerScore;
    }

    public int getAiScore() {
        return aiScore;
    }
}
