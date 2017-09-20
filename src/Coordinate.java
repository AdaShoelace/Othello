/**
 * This class serves as a representation of a coordinate on the game board
 * Created by Pierre Lejdbring on 9/13/17.
 */
public class Coordinate implements Comparable<Coordinate>{
private int row;
    private int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        this.row = coordinate.getRow();
        this.col = coordinate.getCol();
        return 0;
    }
}
