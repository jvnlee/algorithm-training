package programmers.high_score_kit.dp.retries;

public class PRG42898_2 {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1_000_000_007;

        int[][] dp = new int[n + 1][m + 1];

        for (int[] p : puddles) {
            dp[p[1]][p[0]] = -1; // 웅덩이 표시
        }

        dp[1][1] = 1; // 시작점

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i > 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                }

                if (j > 1) {
                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        return dp[n][m];
    }
}
