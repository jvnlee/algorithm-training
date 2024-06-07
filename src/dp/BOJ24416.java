package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ24416 {
    public static int count1, count2;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        fibonacci1(n);

        dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        fibonacci2(n);

        System.out.println(count1 + " " + count2);
    }

    public static int fibonacci1(int n) {
        if (n == 1 || n == 2) {
            count1++;
            return 1;
        } else {
            return fibonacci1(n - 1) + fibonacci1(n - 2);
        }
    }

    public static void fibonacci2(int n) {
        for (int i = 3; i <= n; i++) {
            count2++;
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }
}
