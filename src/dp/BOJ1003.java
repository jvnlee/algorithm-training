package dp;

import java.util.*;

public class BOJ1003 {
    public static Integer[][] count = new Integer[41][2];

    public static Integer[] fibo(int n) {
        if (count[n][0] == null || count[n][1] == null) {
            count[n][0] = fibo(n - 1)[1];
            count[n][1] = fibo(n - 1)[0] + fibo(n - 1)[1];
        }

        return count[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        count[0][0] = 1;
        count[0][1] = 0;
        count[1][0] = 0;
        count[1][1] = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            fibo(n);
            sb.append(count[n][0]).append(" ").append(count[n][1]).append("\n");
        }

        System.out.println(sb);
    }
}
