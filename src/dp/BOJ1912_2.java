package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n]; // dp[i] = nums[0] ~ nums[i] 범위에서 연속된 수의 합의 최대값
        dp[0] = nums[0];

        int max = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 좌변: dp[i - 1]이 양인 경우 (합에 새 값 추가), 우변: dp[i - 1]이 음인 경우 (새 값으로 합 새로 시작)
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
