package algorithm_book;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P182 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];

        int sum = 0;

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(b, Collections.reverseOrder());

        for (int i = 0; i < (n - k); i++) {
            sum += a[i];
        }

        for (int i = 0; i < k; i++) {
            sum += b[i];
        }

        System.out.println(sum);
    }
}
