/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class GameState {

    private int gameGrid[][];

    public GameState() {
        gameGrid = createGrid();
    }

    public GameState(GameState state) {
        gameGrid = state.gameGrid;
    }

    public void setCell(Engine.PlayerType type, Coordinate coord) {
        gameGrid[coord.getRow()][coord.getCol()] = type == Engine.PlayerType.HUMAN ? Utils.HUMAN
                : Utils.AI;
    }

    private int[][] createGrid() {
        int[][] ret = new int[Utils.ROWS][Utils.COLS];
        for(int r = 0; r < Utils.ROWS; ++r) {
            for(int c = 0; c < Utils.COLS; ++c) {
                ret[r][c] = Utils.NONE;
            }
        }
        return ret;
    }

}
