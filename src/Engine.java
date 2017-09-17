/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Engine {
    public enum PlayerType {
        HUMAN(1),
        AI(-1);
        private int value;
        PlayerType(int value)
        {
            this.value = value;
        }
    }

    public Engine() {
    }

    public void placeChip(PlayerType type, Coordinate coord, GameState state) {
        state.setCell(type, coord);
    }

    public GameState getValidMoves(GameState state) {
       return null;
    }

    public boolean hasValidMoves() {
        return true;
    }
}
