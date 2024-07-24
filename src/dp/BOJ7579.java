package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memories = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        int[] costs = new int[n];
        st = new StringTokenizer(br.readLine());
        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }

        int[] dp = new int[totalCost + 1]; // dp[i]: i의 cost를 지불하고 확보할 수 있는 최대 memory
        Arrays.fill(dp, -1);
        dp[0] = 0; // 0의 cost를 지불하고 확보할 수 있는 최대 memory는 0

        for (int i = 0; i < n; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int j = totalCost; j >= cost; j--) {
                if (dp[j - cost] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - cost] + memory);
                }
            }
        }

        for (int i = 0; i <= totalCost; i++) {
            if (dp[i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}
