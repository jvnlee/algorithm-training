package greedy;

import java.util.*;

public class BOJ2437 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] weights = new int[n];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = scanner.nextInt();
        }

        Arrays.sort(weights);

        int accumulated = 0;

        for (int i = 0; i < weights.length; i++) {
            if (accumulated + 1 < weights[i]) break;
            accumulated += weights[i];
        }

        System.out.println(accumulated + 1);
    }
}
