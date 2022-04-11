package dp;

import java.util.*;

public class BOJ9095 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        StringBuilder sb = new StringBuilder();

        int[] memory = new int[11];
        memory[1] = 1;
        memory[2] = 2;
        memory[3] = 4;

        for (int i = 0; i < t; i++) {
             int n = scanner.nextInt();
             for (int j = 4; j <= n; j++) {
                 memory[j] = memory[j - 1] + memory[j - 2] + memory[j - 3];
             }
             sb.append(memory[n] + "\n");
        }

        System.out.println(sb);
    }
}
