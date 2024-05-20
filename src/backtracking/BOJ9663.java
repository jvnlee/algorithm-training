package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    public static int n, answer;
    public static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new boolean[n][n];

        placeQueen(0);

        System.out.println(answer);
    }

    private static void placeQueen(int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row][col] = true;
                placeQueen(row + 1);
                board[row][col] = false;
            }
        }
    }

    private static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }

        for (int i = 1; row - i >= 0 && col + i < n; i++) {
            if (board[row - i][col + i]) {
                return false;
            }
        }

        return true;
    }
}
