/**
 * Created by Pierre Lejdbring on 9/14/17.
 */
public class Utils {

    public final static int ROWS = 8;
    public final static int COLS = 8;
    public final static int AI = -1;
    public final static int HUMAN = 1;
    public final static int NONE = 0;

    private Utils() {

    }

    public static void print(boolean[][] b) {
        for(boolean[] row : b) {
            for(boolean elem: row) {
                System.out.print(elem ? 1 + ", " : 0 + ", ");
            }
            System.out.println();
        }
    }
}
