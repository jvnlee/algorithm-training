package bruteforce;

import java.util.*;

public class BOJ1018 {

    public static int n, m, answer;
    public static boolean[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        answer = 64;

        scanner.nextLine();

        board = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("");
            for (int j = 0; j < m; j++) {
                if (input[j].charAt(0) == 'W') board[i][j] = true;
                else board[i][j] = false;
            }
        }

        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                colorCheck(i, j);
            }
        }

        System.out.println(answer);
    }

    public static void colorCheck(int x, int y) {
        int count = 0;
        boolean firstColor = board[x][y];

        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                if (board[i][j] != firstColor) count++;
                firstColor = !firstColor;
            }
            firstColor = !firstColor;
        }

        count = Math.min(count, 64 - count);
        answer = Math.min(answer, count);
    }
}
