/* *****************************************************************************
 *   Name: Eli Ji
 *   Date: 2-11-20
 *
 *   Description: Queens solver class
 **************************************************************************** */

public class QueensSolver {

    private int dimension;
    private Stack<Location> s;
    private Queue<LocationNode> q;

    public QueensSolver(int dimension){
        this.dimension = dimension;
        s = new Stack<Location>();
        q = new Queue<LocationNode>();
    }

    // Depth First Solution Using Stack
    private int[][] getSolutionDF(){
        int row = 0;
        int col = 0;

        // Find solution;
        while(col < dimension) {
            if(isSafeStack(s, row, col)) {
                s.push(new Location(row, col));
                col++;
                row = 0;
            } else {
                row++;
            }
            // Backtrack
            if(row == dimension){
                Location l = s.pop();
                col--;
                if(l.getRow() == dimension - 1){
                    Location l2 = s.pop();
                    col--;
                    row = l2.getRow() + 1;
                } else {
                    row = l.getRow() + 1;
                }
            }
        }


        // Return Solution
        int[][] board = new int[dimension][dimension];
        // fill in 1 for queens
        for(Location l: s){
            board[l.getRow()][l.getCol()] = 1;
        }
        return board;
    }

    // Breadth First Solution Using Queue
    private int[][] getSolutionBF() {

        LocationNode solutionNode = null;

        // Enqueue first col
        for(int i = 0; i < dimension; i++){
            q.enqueue(new LocationNode(i, 0, null));
        }

        boolean isSolved = false;

        // Find solution
        while(!isSolved) {
            LocationNode current = q.dequeue();
            int row = current.getRow();
            int col = current.getCol();


            for(int i = 0; i<dimension; i ++){
                LocationNode neighbor = new LocationNode(i, col + 1, current);
                // enqueue safe neighbors
                if(isSafeQueue(neighbor)){
                    // check if solved
                    if(col == dimension - 2){
                        solutionNode = neighbor;
                        isSolved = true;
                    }
                    q.enqueue(neighbor);
                }
            }
        }

        // Return solution
        int[][]solutionBoard = new int[dimension][dimension];
        // fill in 1 for queens
        LocationNode curr = solutionNode;
        while(curr != null){
            solutionBoard[curr.getRow()][curr.getCol()] = 1;
            curr = curr.getPreviousNode();
        }
        return solutionBoard;
    }


    boolean isSafeQueue(LocationNode n) {

        int row = n.getRow();
        int col = n.getCol();

        int[][]tempBoard = new int[dimension][dimension];

        // Fill in 1 for Queens Using Node
        LocationNode curr = n.getPreviousNode();
        while(curr != null){
            tempBoard[curr.getRow()][curr.getCol()] = 1;
            curr = curr.getPreviousNode();
        }

        // Checks row for queens
        for (int i = 0; i < col; i++) {
            if (tempBoard[row][i] == 1) {
                return false;
            }
        }
        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (tempBoard[i][j] == 1) {
                return false;
            }
        }
        // Check lower left diagonal
        for (int i = row, j = col; j >= 0 && i < dimension; i++, j--) {
            if (tempBoard[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    // Is placing a queen at row col safe?
    boolean isSafeStack(Stack<Location> s, int row, int col) {

        int[][]tempBoard = new int[dimension][dimension];
        // fill in 1 for queens
        for(Location l: s){
            tempBoard[l.getRow()][l.getCol()] = 1;
        }

       // Checks row for queens
        for (int i = 0; i < col; i++) {
            if (tempBoard[row][i] == 1) {
                return false;
            }
        }
        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (tempBoard[i][j] == 1) {
                return false;
            }
        }
        // Check lower left diagonal
        for (int i = row, j = col; j >= 0 && i < dimension; i++, j--) {
            if (tempBoard[i][j] == 1) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int dimension = 8;
        QueensSolver qs = new QueensSolver(dimension);

        long start = System.currentTimeMillis();
        int[][] solution1 = qs.getSolutionDF();
        long end = System.currentTimeMillis();
        long depthFirstTime = end - start;

        start = System.currentTimeMillis();
        int[][] solution2 = qs.getSolutionBF();
        end = System.currentTimeMillis();
        long breathFirstTime = end - start;

        //int[][] solution = qs.getSolutionBF();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(" " + solution1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Depth First Time: " + depthFirstTime + "ms");
        System.out.println("Breadth First Time: " + breathFirstTime + "ms");
    }
}
