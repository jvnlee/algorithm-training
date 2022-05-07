package greedy;

import java.util.*;

public class BOJ11047 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int count = 0;

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            if (k == 0) break;

            if (k / values[i] >= 1) {
                count += k / values[i];
                k -= (k / values[i]) * values[i];
            }
        }

        System.out.println(count);
    }
}
