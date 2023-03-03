import java.awt.*;
import java.util.Scanner;

public class Main {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        String[] tent = s.split("");
        int[] real = new int[tent.length];

        for (int i = 0; i < real.length; i++) {
            real[i] = Integer.parseInt(tent[i]);
        }

        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        int cnt = 0;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = real[cnt];
                cnt++;
            }
        }

        printBoard(board);

        System.out.println();

        long startTime = System.currentTimeMillis();

        if (solveBoard(board)) {
            long stopTime = System.currentTimeMillis();
            long ans = stopTime - startTime;

            System.out.println("Solved successfully in " + ans + " milliseconds!");
        }
        else {
            System.out.println("Unsolvable board :(");
        }

        System.out.println();

        printBoard(board);

    }


    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * int[][] board = {
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 0}
 *         };
 */

