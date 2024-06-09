package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 1 || n == 2) {
                bw.write(1 + "\n");
                continue;
            }

            long[] dp = new long[n + 1];

            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;

            for (int j = 4; j <= n; j++) {
                dp[j] = dp[j - 2] + dp[j - 3];
            }

            bw.write(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
