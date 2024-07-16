package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());

            int[] files = new int[k + 1]; // 1장 ~ k장
            int[] prefixSum = new int[k + 1]; // 누적합

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= k; j++) {
                files[j] = Integer.parseInt(st.nextToken());
                prefixSum[j] = prefixSum[j - 1] + files[j];
            }

            int[][] dp = new int[k + 1][k + 1]; // dp[a][b] = a장부터 b장까지 합치는 최소 비용

            for (int size = 1; size <= k; size++) { // size = dp[a][b]에서 a ~ b 범위의 크기
                for (int start = 1; start + size <= k; start++) {
                    int end = start + size;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int divider = start; divider < end; divider++) {
                        dp[start][end] = Math.min(
                                dp[start][end],
                                dp[start][divider] + dp[divider + 1][end] + prefixSum[end] - prefixSum[start - 1]
                        );
                    }
                }
            }

            bw.write(dp[1][k] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
