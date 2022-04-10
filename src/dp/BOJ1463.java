package dp;

import java.util.*;

public class BOJ1463 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] memory = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + 1;

            if (i % 3 == 0) {
                memory[i] = Math.min(memory[i], memory[i / 3] + 1);
            }

            if (i % 2 == 0) {
                memory[i] = Math.min(memory[i], memory[i / 2] + 1);
            }
        }

        System.out.println(memory[n]);
    }
}
