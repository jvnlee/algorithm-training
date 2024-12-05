package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 1_000_001;

        // 첫번째 집의 색상 기준으로 진행
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N][3]; // dp[i][j]: i번째 집을 j 색으로 색칠했을 때 누적 색칠 비용

            // 지정한 첫번째 집의 색을 제외한 색칠 비용은 최대값을 초과시켜 고려 대상에서 제외
            for (int color = 0; color < 3; color++) {
                if (color == firstColor) {
                    dp[0][color] = cost[0][color];
                } else {
                    dp[0][color] = 1001;
                }
            }

            // 두번째 집부터 (N - 1)번째 집까지 색칠 (인접한 집은 다른 색을 사용하도록 함)
            for (int house = 1; house < N; house++) {
                int prevHouse = house - 1;

                dp[house][0] = Math.min(dp[prevHouse][1], dp[prevHouse][2]) + cost[house][0];
                dp[house][1] = Math.min(dp[prevHouse][0], dp[prevHouse][2]) + cost[house][1];
                dp[house][2] = Math.min(dp[prevHouse][0], dp[prevHouse][1]) + cost[house][2];
            }

            // 마지막 집의 색이 첫번째 집의 색과 다른 경우에만 유효한 경우이므로 answer 갱신
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    answer = Math.min(answer, dp[N - 1][lastColor]);
                }
            }
        }

        System.out.println(answer);
    }
}
