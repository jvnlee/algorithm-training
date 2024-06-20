package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stuffs = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stuffs[i][0] = Integer.parseInt(st.nextToken());
            stuffs[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[k + 1]; // ∂p[i] = 무게 합 i로 가질 수 있는 최대 Value

        for (int i = 0; i < stuffs.length; i++) {
            int weight = stuffs[i][0];
            int value = stuffs[i][1];
            for (int j = k; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[k]);
    }
}
