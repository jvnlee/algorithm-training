package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] matrixInfo = new int[n + 1][2];

        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            matrixInfo[i][0] = Integer.parseInt(st.nextToken());
            matrixInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int gap = 1; gap < n; gap++) {
            for (int start = 1; start + gap <= n; start++) {
                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;

                for (int divider = start; divider < end; divider++) {
                    dp[start][end] = Math.min(
                            dp[start][end],
                            dp[start][divider] + dp[divider + 1][end]
                                    + matrixInfo[start][0] * matrixInfo[divider][1] * matrixInfo[end][1]
                    );
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}
