package bruteforce;

import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ1065 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (i < 100) count++;
            else {
                int[] arr = Stream.of(String.valueOf(i).split("")).mapToInt(Integer::parseInt).toArray();
                int diff = arr[1] - arr[0];
                for (int j = 1; j < arr.length; j++) {
                    if ((arr[j] - arr[j - 1]) != diff) break;
                    if (j == arr.length - 1) count++;
                }
            }
        }

        System.out.println(count);
    }
}
