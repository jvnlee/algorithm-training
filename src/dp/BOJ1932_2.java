package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1932_2 {
    // Bottom-Up
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] nums = new int[n][n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            Arrays.fill(nums[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = nums[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + nums[i][j];
            }
        }

        System.out.println(dp[0][0]);
    }

    /*
    // Top-Down
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] nums = new int[n][n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            Arrays.fill(nums[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][n];

        dp[0][0] = nums[0][0];

        for (int i = 1; i < n; i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + nums[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + nums[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + nums[i][j];
                }
            }
        }

        Arrays.sort(dp[n - 1]);

        System.out.println(dp[n - 1][n - 1]);
    }
     */
}
