package dp;

import java.util.*;

public class BOJ2839 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int num = 0;

        while (true) {
            if (n % 5 == 0) {
                num += (n / 5);
                System.out.println(num);
                break;
            } else {
                n -= 3;
                num++;
            }
            if (n < 0) {
                System.out.println(-1);
                break;
            }
        }
    }
}
