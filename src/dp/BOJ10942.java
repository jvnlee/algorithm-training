package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][N + 1];

        // 한자리 팰린드롬
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 두자리 팰린드롬
        for (int i = 1; i < N; i++) {
            if (nums[i] == nums[i + 1]) { // 연속하는 같은 수는 팰린드롬이다.
                dp[i][i + 1] = 1;
            }
        }

        // 세자리 이상 팰린드롬
        for (int len = 3; len <= N; len++) {
            for (int start = 1; start <= N - len + 1; start++) {
                int end = start + len - 1;

                if (nums[start] == nums[end] && dp[start + 1][end - 1] == 1) { // 시작과 끝이 같고 안쪽이 팰린드롬이면 전체도 팰린드롬이다.
                    dp[start][end] = 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bw.write(dp[from][to] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
