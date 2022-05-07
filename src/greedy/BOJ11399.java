package greedy;

import java.util.*;

public class BOJ11399 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = scanner.nextInt();
        }

        Arrays.sort(time);

        int[] sum = new int[n];

        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j <= i; j++) {
                tmp += time[j];
            }
            sum[i] = tmp;
        }

        int answer = Arrays.stream(sum).sum();
        System.out.println(answer);
    }
}
