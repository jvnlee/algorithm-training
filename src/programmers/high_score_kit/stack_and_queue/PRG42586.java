package programmers.high_score_kit.stack_and_queue;

import java.util.*;

public class PRG42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);

            q.offer(day);
        }

        List<Integer> list = new ArrayList<>();

        while (!q.isEmpty()) {
            int poll = q.poll();
            int count = 1;

            while (!q.isEmpty() && q.peek() <= poll) {
                q.poll();
                count++;
            }

            list.add(count);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}