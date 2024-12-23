package programmers.high_score_kit.brute_force;

import java.util.*;

public class PRG42840 {
    public int[] solution(int[] answers) {
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int firstTotal = 0;
        int secondTotal = 0;
        int thirdTotal = 0;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i % 5]) {
                firstTotal++;
            }

            if (answers[i] == second[i % 8]) {
                secondTotal++;
            }

            if (answers[i] == third[i % 10]) {
                thirdTotal++;
            }
        }

        int max = Math.max(firstTotal, Math.max(secondTotal, thirdTotal));
        List<Integer> list = new ArrayList<>();

        if (firstTotal == max) {
            list.add(1);
        }

        if (secondTotal == max) {
            list.add(2);
        }

        if (thirdTotal == max) {
            list.add(3);
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
