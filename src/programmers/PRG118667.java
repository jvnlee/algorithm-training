package programmers;

import java.util.*;
import java.util.stream.*;

public class PRG118667 {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Double> q1 = Arrays.stream(queue1).mapToDouble(i -> i).boxed().collect(Collectors.toCollection(LinkedList::new));
        Queue<Double> q2 = Arrays.stream(queue2).mapToDouble(i -> i).boxed().collect(Collectors.toCollection(LinkedList::new));

        double sum1 = q1.stream().reduce(0d, Double::sum).longValue();
        double sum2 = q2.stream().reduce(0d, Double::sum).longValue();

        int count = 0;
        int maxCount = (q1.size() + q2.size()) * 2;

        while (sum1 != sum2) {
            count++;

            Double poll;

            if (sum1 > sum2) {
                poll = q1.poll();
                sum1 -= poll;
                sum2 += poll;
                q2.offer(poll);
            } else {
                poll = q2.poll();
                sum1 += poll;
                sum2 -= poll;
                q1.offer(poll);
            }

            if (count > maxCount) {
                return -1;
            }
        }

        return count;
    }
}
