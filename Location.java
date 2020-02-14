/* *****************************************************************************
 *   Name: Eli Ji
 *   Date: 2-11-20
 *
 *   Description: Location class used in QueensSolver
 **************************************************************************** */

public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
