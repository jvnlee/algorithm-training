package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int[] dp = new int[k + 1]; // dp[x]: x원을 만드는데 필요한 최소 동전 개수

        int max = 10000 / coins[0] + coins[0];

        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 0; i < n ; i++) { // 동전 사용 가지수를 하나씩 늘려가며 필요한 최소 개수 dp 갱신
            for (int j = coins[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if (dp[k] == max) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
