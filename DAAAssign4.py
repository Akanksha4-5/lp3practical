# ===========================================================
# Sr. No. 4
# DESIGN AND ANALYSIS OF ALGORITHMS
#
# Title: N-Queens Problem Using Backtracking
#
# CO4, CO5
#
# Program: Design an N-Queens matrix with the first Queen already placed and use Backtracking to place the remaining Queens.
# ===========================================================

N = 8

def print_solution(board):
    print("N-Queens Solution:")
    for row in board:
        print(*row, "")

# only rows above are checked, because we fill row by row
def is_safe(board, row, col):
    for i in range(row):
        if board[i][col] == 1:
            return False
    i, j = row - 1, col - 1
    while i >= 0 and j >= 0:
        if board[i][j] == 1:
            return False
        i, j = i - 1, j - 1
    i, j = row - 1, col + 1
    while i >= 0 and j < N:
        if board[i][j] == 1:
            return False
        i, j = i - 1, j + 1
    return True

def solve_nqueens(board, row):
    if row >= N:
        return True
    for col in range(N):
        if is_safe(board, row, col):
            board[row][col] = 1                    # place
            if solve_nqueens(board, row + 1):
                return True
            board[row][col] = 0                    # backtrack
    return False

board = [[0] * N for _ in range(N)]
board[0][3] = 1                                    # first queen fixed at row 0, col 3
if solve_nqueens(board, 1):
    print_solution(board)
else:
    print("No solution exists.")


# ==================== OUTPUT ====================
# N-Queens Solution:
# 0 0 0 1 0 0 0 0 
# 1 0 0 0 0 0 0 0 
# 0 0 0 0 1 0 0 0 
# 0 0 0 0 0 0 0 1 
# 0 1 0 0 0 0 0 0 
# 0 0 0 0 0 0 1 0 
# 0 0 1 0 0 0 0 0 
# 0 0 0 0 0 1 0 0 
# ================================================
