package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1]; // dp[i]: i를 1로 만드는 데 필요한 연산 횟수의 최소값
        int[] track = new int[N + 1]; // track[i]: i에 연산을 한번 적용했을 때의 값 (i -> 1로 가는 과정에서 i의 바로 다음 값)

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                track[i] = i / 3;
            }

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                track[i] = i / 2;
            }

            if (dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                track[i] = i - 1;
            }
        }

        bw.write(dp[N] + "\n");

        while (N > 0) {
            bw.write(N + " ");
            N = track[N];
        }

        bw.flush();
        bw.close();
    }
}
