package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long DIV = 1_000_000_000L;

        long[][] dp = new long[n + 1][10];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1] % DIV;

            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % DIV + dp[i - 1][j + 1] % DIV) % DIV;
            }

            dp[i][9] = dp[i - 1][8] % DIV;
        }

        long answer = 0;

        for (int i = 1; i <= 9; i++) {
            answer += dp[n][i];
            answer %= DIV;
        }

        System.out.println(answer);
    }
}
