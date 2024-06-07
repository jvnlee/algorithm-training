package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9184 {
    public static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            bw.write(String.format("w(%d, %d, %d) = %d\n", a, b, c, memoization(a, b, c)));
        }

        bw.flush();
        bw.close();
    }

    public static int memoization(int a, int b, int c) {
        boolean validRange = 0 <= a && a <= 20 && 0 <= b && b <= 20 && 0 <= c && c <= 20;

        if (validRange && dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return dp[20][20][20]
                    = memoization(20, 20, 20);
        }

        if (a < b && b < c) {
            return dp[a][b][c]
                    = memoization(a, b, c - 1)
                    + memoization(a, b - 1, c - 1)
                    - memoization(a, b - 1, c);
        }

        return dp[a][b][c]
                = memoization(a - 1, b, c)
                + memoization(a - 1, b - 1, c)
                + memoization(a - 1, b , c - 1)
                - memoization(a - 1, b - 1, c - 1);
    }

}
