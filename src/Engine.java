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

    public void placeChip(PlayerType type, Coordinate coord) {
        if(type == PlayerType.HUMAN) {
        } else {

        }
    }
}
