package programmers.high_score_kit.dp.retries;

import java.util.*;

public class PRG42895_2 {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        List<Set<Integer>> dp = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 1; i <= 8; i++) {
            dp.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));

            for (int j = 1; j < i; j++) {
                for (int n1 : dp.get(j)) {
                    for (int n2 : dp.get(i - j)) {
                        Set<Integer> set = dp.get(i);

                        set.add(n1 + n2);

                        if (n1 - n2 >= 1) {
                            set.add(n1 - n2);
                        }

                        if (n2 - n1 >= 1) {
                            set.add(n2 - n1);
                        }

                        set.add(n1 * n2);

                        if (n1 != 0 && n2 % n1 == 0) {
                            set.add(n2 / n1);
                        }

                        if (n2 != 0 && n1 % n2 == 0) {
                            set.add(n1 / n2);
                        }
                    }
                }
            }

            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}