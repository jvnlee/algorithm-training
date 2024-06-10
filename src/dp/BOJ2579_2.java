package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_2 {
    // Bottom-up
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] stairs = new int[n + 1];

        // stairs[0] = 0 (시작점)
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n + 1];

        dp[1] = stairs[1];

        if (n == 1) {
            System.out.println(dp[1]);
            return;
        }

        dp[2] = stairs[1] + stairs[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = stairs[i] + Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]);
        }

        System.out.println(dp[n]);
    }

    /*
    // Top-down
    public static int[] stairs;
    public static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        stairs = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp = new Integer[n + 1];

        dp[0] = 0;
        dp[1] = stairs[1];

        if (n == 1) {
            System.out.println(dp[1]);
            return;
        }

        dp[2] = stairs[1] + stairs[2];

        System.out.println(solve(n));
    }

    public static int solve(int n) {
        if (dp[n] == null) {
            dp[n] = stairs[n] + Math.max(solve(n - 3) + stairs[n - 1], solve(n - 2));
        }
        return dp[n];
    }
     */
}
