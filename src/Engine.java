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

    private GameState state;

    public Engine(GameState state) {
        this.state = state;
    }

    public void placeChip(PlayerType type, Coordinate coord) {
        state.setCell(type, coord);
    }

    public boolean hasValidMoves() {
        return true;
    }
}
