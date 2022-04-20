package dp;

import java.util.*;

public class BOJ1932 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Integer[][] pyramid = new Integer[n][n];

        for (int i = 0; i < pyramid.length; i++) {
            for (int j = 0; j <= i; j++) {
                pyramid[i][j] = scanner.nextInt();
            }
        }

        Integer[][] dp = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = pyramid[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + pyramid[i][j];
            }
        }

        System.out.println(dp[0][0]);
    }
}
