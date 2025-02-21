package programmers.high_score_kit.dp.retries;

import java.util.*;

public class PRG43105_2 {
    public int solution(int[][] triangle) {
        int len = triangle.length;

        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = triangle[i][j] + dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }

        Arrays.sort(dp[len - 1]);

        return dp[len - 1][len - 1];
    }
}