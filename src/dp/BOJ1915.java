package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int[][] dp = new int[n][m]; // dp[i][j]: (i, j)가 1인 경우, (i, j)를 포함하여 만들 수 있는 가장 큰 정사각형의 변 길이
        int len = 0;

        for (int i = 0; i < m; i++) {
            dp[0][i] = arr[0][i];
            len = Math.max(len, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = arr[i][0];
            len = Math.max(len, dp[i][0]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int top = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    int topLeft = dp[i - 1][j - 1];

                    dp[i][j] = Math.min(top, Math.min(left, topLeft)) + 1;
                    len = Math.max(len, dp[i][j]);
                }
            }
        }

        System.out.println(len * len);
    }
}
