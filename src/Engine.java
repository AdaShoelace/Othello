/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Engine {

    private static final int NO_DIRECTION = 0;
    private static final int WEST = -1;
    private static final int EAST = 1;
    private static final int NORTH = -1;
    private static final int SOUTH = 1;

    private Engine() {
    }

    public static void placeChip(int type, Coordinate coord, GameState state) {
        if(coord == null) return;
        state.setCell(type, coord);
    }

    public static boolean isValidMove(Coordinate coord, GameState state, int player) {
        return checkDirection(NO_DIRECTION, WEST, coord, state, player)
                || checkDirection(NO_DIRECTION, EAST, coord, state, player)
                || checkDirection(NORTH, NO_DIRECTION, coord, state, player)
                || checkDirection(SOUTH, NO_DIRECTION, coord, state, player)
                || checkDirection(NORTH, WEST, coord, state, player)
                || checkDirection(NORTH, EAST, coord, state, player)
                || checkDirection(SOUTH, WEST, coord, state, player)
                || checkDirection(SOUTH, EAST, coord, state, player);
    }

    private static boolean checkDirection(int deltaRow, int deltaCol, Coordinate coord, GameState state, int player) {

        int row = coord.getRow();
        int col = coord.getCol();
        int[][] grid = state.getGrid();

        if(row + deltaRow >= 0
                && row + deltaRow < grid.length
                && col + deltaCol >= 0
                && col + deltaCol < grid[0].length
                && grid[row + deltaRow][col + deltaCol] != -player) {
            return false;
        }

        row += 2 * deltaRow;
        col += 2 * deltaCol;

        while(row < Utils.ROWS && row >= 0 && col < Utils.COLS && col >= 0) {
            if(grid[row][col] == Utils.NONE) {
                return false;
            } else if(grid[row][col] == Utils.HUMAN) {
                return true;
            }
            row += deltaRow;
            col += deltaCol;
        }
        return false;
    }

}
