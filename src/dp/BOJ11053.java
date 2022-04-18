package dp;

import java.util.*;

public class BOJ11053 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] sequence = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(dp[i], answer);
        }

        System.out.println(answer);
    }
}
