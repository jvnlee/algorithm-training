package greedy;

import java.util.*;

public class BOJ1541 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = Integer.MAX_VALUE;
        String[] subtract = scanner.nextLine().split("-");

        for (int i = 0; i < subtract.length; i++) {
            int tmp = 0;

            String[] add = subtract[i].split("\\+");

            for (int j = 0; j < add.length; j++) {
                tmp += Integer.parseInt(add[j]);
            }

            if (sum == Integer.MAX_VALUE) sum = tmp;
            else sum -= tmp;
        }

        System.out.println(sum);
    }
}
