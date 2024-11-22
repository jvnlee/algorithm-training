package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1311 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] cost = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][1 << (N + 1)];
        // dp[i][j]: i번째 사람에게 일을 시켜야 함. 그 전 사람까지 일을 배분해준 조합은 j의 비트로 나타냄

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) { // 사람 하나씩 늘리기
            for (int j = 0; j < 1 << (N + 1); j++) { // 000...00 부터 111...11 까지 모든 경우의 수를 나타내는 비트
                if (dp[i - 1][j] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int k = 1; k <= N; k++) { // i번째 사람에게 k번째 일 배정해보기
                    if ((j & (1 << k)) != 0) { // 이미 수행된 일인 경우
                        continue;
                    }

                    // 일 시키고 비용 더해서 총 비용이 줄었다면 최소값 갱신하기
                    dp[i][j | (1 << k)] = Math.min(dp[i][j | (1 << k)], dp[i - 1][j] + cost[i][k]);
                }
            }
        }

        System.out.println(dp[N][(1 << (N + 1)) - 2]);
    }
}
