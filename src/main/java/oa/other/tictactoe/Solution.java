package oa.other.tictactoe;

public class Solution {

    public static char CHAR_O = 'O';
    public static char CHAR_X = 'X';

    public static boolean validTicTacToe(String[] board) {
        // your code here
        if (board == null || board.length != 3) {
            return false;
        }

        int numO = 0;
        int numX = 0;
        int n = board.length;
        for (int i = 0; i < n; i++) {
            String row = board[i];
            if (row.length() != 3) {
                return false;
            }

            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                // System.out.println(row + " c: " + c);
                if (c != CHAR_O && c != CHAR_X && c != ' ') {
                    return false;
                }

                if (c == CHAR_O) {
                    numO++;
                    continue;
                }
                if (c == CHAR_X) {
                    numX++;
                    continue;
                }
            }
        }


        int diff = numX - numO;
        // System.out.println(diff + " " + numX + " " + numO);
        if (diff != 0 && diff != 1) {
            return false;
        }

        // "XXX",
        // "OOO",
        // "   "},

        int numWin = 0;
        int m = board[0].length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (board[i].charAt(j) != board[i].charAt(j + 1)) {
                    break;
                }
                // 0 1 2 -> 3
                if (j == m - 2 && board[i].charAt(j) != ' ') {
                    numWin++;
                }
            }
        }

        // "XO ",
        // "XO ",
        // "XO "},
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (board[i].charAt(j) != board[i + 1].charAt(j)) {
                    break;
                }
                // 0 1 2 -> 3
                if (i == n - 2 && board[i].charAt(j) != ' ') {
                    numWin++;
                }
            }
        }


        if (numWin > 1) {
            return false;
        }

        if (numWin == 1 && diff == 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        TicTacToeGame[] games = new TicTacToeGame[]{
                new TicTacToeGame(
                        "board 1",
                        new String[]{

                                "O  ",
                                "   ",
                                "   "},

                        false),

                new TicTacToeGame(
                        "board 2",
                        new String[]{

                                "XOX",
                                " X ",
                                "   "},

                        false),

                new TicTacToeGame(
                        "board 3",
                        new String[]{

                                "XOX",
                                "O O",
                                "XOX"},

                        true),

                new TicTacToeGame(
                        "board 4",
                        null,
                        false),

                new TicTacToeGame(
                        "board 5",
                        new String[]{

                                "XOXXXX",
                                "O O",
                                "XOX"},

                        false),

                new TicTacToeGame(
                        "board 6",
                        new String[]{

                                "XOX",
                                "O O",
                                "O O",
                                "XOX"},

                        false),

                new TicTacToeGame(
                        "board 7",
                        new String[]{

                                "XOX",
                                "O A",
                                "XOX"},

                        false),

                new TicTacToeGame(
                        "board 8",
                        new String[]{

                                "   ",
                                "   ",
                                "   "},

                        true),

                new TicTacToeGame(
                        "board 9",
                        new String[]{

                                "X  ",
                                "   ",
                                "   "},

                        true),

                new TicTacToeGame(
                        "board 10",
                        new String[]{

                                "XXX",
                                "OOO",
                                "   "},

                        false),

                new TicTacToeGame(
                        "board 11",
                        new String[]{

                                "XO ",
                                "XO ",
                                "XO "},

                        false),

                new TicTacToeGame(
                        "board 12",
                        new String[]{

                                "XOO",
                                "XO ",
                                "X  "},

                        false),
        };




        for (TicTacToeGame game : games) {

            if (game.expected == validTicTacToe(game.board)) {
                System.out.println(game.name + " passed!");
            } else {
                System.out.println(game.name + " did not pass :(");
            }
        }
    }
}

