package dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());

        int[] coins = new int[n];

        for (int i = 0; i < coins.length; i++) {
            coins[i] = parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];

        dp[0] = 1; // 0원을 만드는 방법은 오직 1가지

        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            // coin원 짜리 동전으로 x부터 k 값까지 만드는 방법의 수를 기록
            for (int j = coin; j < k + 1; j++) {
                dp[j] += dp[j - coin]; // 이전 단위의 동전으로 만들 수 있었던 방법의 수에 새로운 단위를 사용하는 방법 추가
            }
        }

        System.out.println(dp[k]);
    }

    /*
    - | 0 1 2 3 4 5 6 7 8 9 10
    1 | - 1 1 1 1 1 1 1 1 1 1
    2 | - 1 2 2 3 3 4 4 5 5 6
    5 | - 1 2 2 3 4 5 6 7 8 10
     */
}
