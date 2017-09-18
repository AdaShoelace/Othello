/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class Engine {

    public Engine() {
    }

    public void placeChip(int type, Coordinate coord, GameState state) {
        if(coord == null) return;
        state.setCell(type, coord);
    }

    public GameState getValidMoves(GameState state) {
       return null;
    }

    public boolean hasValidMoves() {
        return true;
    }
}
