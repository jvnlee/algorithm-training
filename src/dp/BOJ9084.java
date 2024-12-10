package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int price = coin; price <= M; price++) {
                    dp[price] += dp[price - coin];
                }
            }

            answer.append(dp[M]).append("\n");
        }

        System.out.println(answer);
    }
}
