package leetcode._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.
You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens'
placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Example 2:
Input: n = 1
Output: [["Q"]]
 */
public class _51_NQueens {

    static List<List<String>> result = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0);
        return result;
    }

    private static void backtrack(char[][] board, int row) {
        if (row == board.length) {
            result.add(char2List(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (!isValid(board, row, i)) {
                continue;
            }
            board[row][i] = 'Q';
            backtrack(board, row + 1);
            board[row][i] = '.';
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        int n = board.length;

        // up side no Q
        for (int i = row; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // left side no Q
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // right side no Q
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static List<String> char2List(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(String.valueOf(row));
        }
        return list;
    }


    public static void main(String[] args) {
        System.out.println("[[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]] " +
                "equals\n" + solveNQueens(4));
        System.out.println("[[Q]] equals " + solveNQueens(1));
    }
}
