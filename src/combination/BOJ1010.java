package combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[31][31];

        for (int i = 1; i <= 30; i++) {
            dp[i][1] = i; // iC1 = i
        }

        for (int i = 2; i <= 30; i++) {
            for (int j = 2; j <= 30; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // nCr = n-1Cr-1 + n-1Cr
            }
        }

        StringTokenizer st;

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            bw.write(dp[m][n] + "\n"); // mCn (단순히 조합 계산법으로 구하면 정수형 초과범위의 숫자를 다루게 되면서 값이 부정확해짐)
        }

        bw.flush();
        bw.close();
    }
}
