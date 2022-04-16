package bruteforce;

import java.util.*;

public class BOJ2798 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;
            temp += cards[i];
            for (int j = i + 1; j < n; j++) {
                temp += cards[j];
                for (int k = j + 1; k < n; k++) {
                    temp += cards[k];
                    if (temp <= m) {
                        sum = Math.max(sum, temp);
                    }
                    temp -= cards[k];
                }
                temp -= cards[j];
            }
        }

        System.out.println(sum);
    }
}
