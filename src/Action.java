/**
 * Class holding the utility getting passed on up in the tree as alpha or beta
 * and also the coordinate that might be the best move
 * Created by Pierre Lejdbring on 9/23/17.
 */
public class Action {
    Coordinate c;
    int utility;

    Action(Coordinate c, int utility) {
        this.c = c;
        this.utility = utility;
    }
}
