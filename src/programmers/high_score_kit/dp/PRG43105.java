package programmers.high_score_kit.dp;

import java.util.*;

public class PRG43105 {
    public int solution(int[][] triangle) {
        int len = triangle.length;

        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];

            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        Arrays.sort(dp[len - 1]);

        return dp[len - 1][len - 1];
    }
}
