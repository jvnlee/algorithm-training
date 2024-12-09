package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 2];
        int[] P = new int[N + 2];

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2]; // dp[i]: (i - 1)번째 날까지 얻을 수 있는 최대 수익
        int max = 0;

        for (int day = 1; day <= N + 1; day++) {
            max = Math.max(max, dp[day]);

            int endDay = day + T[day] - 1;
            if (endDay <= N) {
                dp[endDay + 1] = Math.max(dp[endDay + 1], max + P[day]); // 작업 끝난 다음날 정산
            }
        }

        System.out.println(max);
    }
}
