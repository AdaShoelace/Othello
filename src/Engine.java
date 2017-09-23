import java.util.ArrayList;

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

    /**
     * Updates the board by setting the coordinates between the latest chip added to the board
     * and the ones that were already there
     * @param state
     * @param coord
     * @param player
     * @return
     */
    public static GameState updateBoard(GameState state, Coordinate coord, int player) {
        if(isValidMove(coord, state, player) && state.getGrid()[coord.getRow()][coord.getCol()] == Utils.NONE) {
            GameState postMove = new GameState(state);
            postMove.setCell(player, coord);

            postMove = flipChips(NO_DIRECTION, WEST, coord, postMove, player);
            postMove = flipChips(NO_DIRECTION, EAST, coord, postMove, player);
            postMove = flipChips(NORTH, NO_DIRECTION, coord, postMove, player);
            postMove = flipChips(SOUTH, NO_DIRECTION, coord, postMove, player);
            postMove = flipChips(NORTH, WEST, coord, postMove, player);
            postMove = flipChips(NORTH, EAST, coord, postMove, player);
            postMove = flipChips(SOUTH, WEST, coord, postMove, player);
            postMove = flipChips(SOUTH, EAST, coord, postMove, player);

            return postMove;
        }
        return state;
    }

    /**
     * Generates a list of all the possible states the game can take based on the state passed to the method
     * @param state
     * @param player
     * @return list of possible states
     */
    public static ArrayList<GameState> generatePossibleStates(GameState state, int player) {
        boolean[][] possibleMoves = getValidMoves(state, player);
        ArrayList<GameState> possibleStates = new ArrayList<>();

        for(int row = 0; row < possibleMoves.length; row++) {
            for(int col = 0; col < possibleMoves[0].length; col++) {
                if(possibleMoves[row][col]) {
                    GameState gsCpy = new GameState(state);
                    possibleStates.add(Engine.updateBoard(gsCpy, new Coordinate(row, col), player));
                }
            }
        }
        return possibleStates;
    }

    public static ArrayList<GameState> generatePossibleStates2(GameState state, int player) {
        ArrayList<GameState> possibleStates = new ArrayList<>();

        for(int row = 0; row < state.getGrid().length; row++) {
            for(int col = 0; col < state.getGrid()[0].length; col++) {
                if(isValidMove(new Coordinate(row, col), new GameState(state), player)) {
                    GameState gsCpy = new GameState(state);
                    possibleStates.add(Engine.updateBoard(gsCpy, new Coordinate(row, col), player));
                }
            }
        }
        return possibleStates;
    }


    public static GameState getFirstPossibleState(GameState gState, int player) {
        GameState ret = new GameState(gState);
        boolean[][] moves = getValidMoves(ret, player);
        for(int row = 0; row < moves.length; row++) {
            for(int col = 0; col < moves[0].length; col++) {
                if(moves[row][col]) {
                    return Engine.updateBoard(ret, new Coordinate(row, col), player);
                }
            }
        }
        return gState;
    }

    /**
     * Checks if the move the players wants to make is valid or not
     * @param coord
     * @param gState
     * @param player
     * @return
     */
    public static boolean isValidMove(Coordinate coord, GameState gState, int player) {
        GameState state = new GameState(gState);
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
            } else if(grid[row][col] == player) {
                return true;
            }
            row += deltaRow;
            col += deltaCol;
        }
        return false;
    }

    private static GameState flipChips(int deltaRow, int deltaCol, Coordinate coord, GameState gState, int player) {
        GameState state = new GameState(gState);
        int row = coord.getRow() + deltaRow;
        int col = coord.getCol() + deltaCol;
        int[][] grid = state.getGrid();

        while(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
            if(grid[row][col] == Utils.NONE) {
                break;
            } else if(grid[row][col] == player) {
                while(row != coord.getRow() || col != coord.getCol()) {
                    state.setCell(player, new Coordinate(row, col));

                    row -= deltaRow;
                    col -= deltaCol;
                }
                break;
            }
            row += deltaRow;
            col += deltaCol;
        }
        return state;
    }

    /**
     * Calculate the valid moves for the given player in the given state
     * @param gState
     * @param player
     * @return a boolean matrix of valid coordinates
     */
    public static boolean[][] getValidMoves(GameState gState, int player) {
        GameState state = new GameState(gState);
        boolean[][] validMoves = new boolean[state.getGrid().length][state.getGrid()[0].length];
        for(int row = 0; row < state.getGrid().length; row++) {
            for(int col = 0; col < state.getGrid()[0].length; col++) {
                validMoves[row][col] = state.getGrid()[row][col] == Utils.NONE &&
                        isValidMove(new Coordinate(row, col), state, player);
            }
        }
        return validMoves;
    }

    /**
     * Checks if the given player has any valid moves to make in the given state
     * @param state
     * @param player
     * @return
     */
    public static boolean hasValidMoves(GameState state, int player) {
        for(int row = 0; row < state.getGrid().length; row++) {
            for(int col = 0; col < state.getGrid()[0].length; col++) {
                if(isValidMove(new Coordinate(row, col), state, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the number of valid moves for the given player in the given state
     * @param state
     * @param player
     * @return
     */
    public static int validMoveCount(GameState state, int player) {
        boolean[][] board = getValidMoves(state, player);
        int count = 0;
        for(boolean[] row : board) {
            for(boolean elem : row) {
                count += elem ? 1 : 0;
            }
        }
        return count;
    }

    /**
     * Checks wether the given state is a final state or not
     * @param state
     * @return
     */
    public static boolean gameOver(GameState state) {
        if(hasValidMoves(state, Utils.AI) || hasValidMoves(state, Utils.HUMAN))
            return false;
        else
            return true;
    }

    /**
     * Calculates the score for the given state.
     * Positive - human player is in the lead
     * Negative - AI is in the lead
     * Zero - it's a tie
     * @param state
     * @return
     */
    public static int calculateScore(GameState state) {
        int score = 0;

        for(int[] row : state.getGrid()) {
            for(int elem : row) {
                score += elem;
            }
        }
        return score;
    }

}
