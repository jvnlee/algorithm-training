package dp;

import java.util.*;

public class BOJ1149 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        int[][] cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");

            for (int j = 0; j <= 2; j++) {
                cost[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 1; i < n; i++) {
            cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
            cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
            cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
        }

        System.out.println(Math.min(Math.min(cost[n - 1][0], cost[n - 1][1]), cost[n - 1][2]));
    }
}
