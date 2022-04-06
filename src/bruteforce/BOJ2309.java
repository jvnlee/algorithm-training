package bruteforce;

import java.util.*;

public class BOJ2309 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] heights = new int[9];
        int sum = 0;
        int fake1 = 0, fake2 = 0;

        for (int i = 0; i < heights.length; i++) {
            heights[i] = scanner.nextInt();
            sum += heights[i];
        }

        Arrays.sort(heights);

        for (int i = 0; i < heights.length - 1; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                if (sum - heights[i] - heights[j] == 100) {
                    fake1 = i;
                    fake2 = j;
                    break;
                }
            }
        }

        for (int i = 0; i < heights.length; i++) {
            if (i == fake1 || i == fake2) continue;
            System.out.println(heights[i]);
        }

    }
}
