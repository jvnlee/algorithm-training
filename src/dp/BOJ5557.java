package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int target = nums[N - 1];

        long[][] dp = new long[N - 1][21]; // dp[i][j]: 0 ~ i 인덱스까지의 숫자를 연산하여 중간 결과값 j를 만드는 방법의 수

        dp[0][nums[0]] = 1;

        for (int i = 1; i < N - 1; i++) { // nums의 숫자를 하나씩 활용
            for (int j = 0; j <= 20; j++) { // 중간 결과값의 허용 범위인 0 ~ 20 사이만 순회
                if (j + nums[i] <= 20) {
                    dp[i][j + nums[i]] += dp[i - 1][j];
                }

                if (j - nums[i] >= 0) {
                    dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N - 2][target]);
    }
}
