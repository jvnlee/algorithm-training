package bruteforce;

import java.util.*;

public class BOJ2231 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int result = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int tmp = i;

            while (tmp != 0) {
                sum += tmp % 10;
                tmp /= 10;
            }

            if (sum + i == n) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
