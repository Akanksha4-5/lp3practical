/*
===========================================================
Sr. No. 4
DESIGN AND ANALYSIS OF ALGORITHMS

Title: N-Queens Problem Using Backtracking

CO4, CO5

Program: Design an N-Queens matrix with the first Queen already placed and use Backtracking to place the remaining Queens.
===========================================================
*/

public class DAAAssign4 {
    static int N = 8;

    static void printSolution(int[][] board) {
        System.out.println("N-Queens Solution:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    // only rows above are checked, because we fill row by row
    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 1) return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) if (board[i][j] == 1) return false;
        return true;
    }

    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) return true;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;                       // place
                if (solveNQueens(board, row + 1)) return true;
                board[row][col] = 0;                       // backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        board[0][3] = 1;                                   // first queen fixed at row 0, col 3
        if (solveNQueens(board, 1)) printSolution(board);
        else System.out.println("No solution exists.");
    }
}


/*
==================== OUTPUT ====================
N-Queens Solution:
0 0 0 1 0 0 0 0 
1 0 0 0 0 0 0 0 
0 0 0 0 1 0 0 0 
0 0 0 0 0 0 0 1 
0 1 0 0 0 0 0 0 
0 0 0 0 0 0 1 0 
0 0 1 0 0 0 0 0 
0 0 0 0 0 1 0 0 
================================================
*/
