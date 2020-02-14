/* *****************************************************************************
 *   Name: Eli Ji
 *   Date: 2-11-20
 *
 *   Description: Location node class used in QueensSolver
 **************************************************************************** */

public class LocationNode {
    private int row;
    private int col;
    private LocationNode previousNode;

    public LocationNode(int row, int col, LocationNode previousNode) {
        this.row = row;
        this.col = col;
        this.previousNode = previousNode;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public LocationNode getPreviousNode() {return previousNode;}

    public void setPreviousNode(LocationNode prev) {
        previousNode = prev;
    }
}
