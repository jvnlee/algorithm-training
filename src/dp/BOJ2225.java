package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int MOD = 1_000_000_000;

        int[][] dp = new int[K + 1][N + 1]; // 숫자의 개수 K를 고정 시킨 채로 합 0부터 N까지 만들기

        for (int k = 1; k <= K; k++) {
            dp[k][0] = 1;
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[k][n] = (dp[k - 1][n] + dp[k][n - 1]) % MOD;
            }
        }

        System.out.println(dp[K][N]);
    }
}
