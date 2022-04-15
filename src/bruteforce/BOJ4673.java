package bruteforce;

import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ4673 {

    public static void main(String[] args) {
        int[] nums = new int[10001];

        for (int i = 1; i <= 10000; i++) {
            int[] ints = Stream.of(String.valueOf(i).split("")).mapToInt(Integer::parseInt).toArray();
            int x = i + Arrays.stream(ints).sum();
            if (x >= 1 && x <= 10000) {
                nums[x] = x;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (nums[i] == 0) System.out.println(i);
        }
    }
}
