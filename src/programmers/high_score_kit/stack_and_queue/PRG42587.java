package programmers.high_score_kit.stack_and_queue;

import java.util.*;

public class PRG42587 {
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            q.offer(new Process(i, priorities[i]));
            pq.offer(priorities[i]);
        }

        int answer = 0;

        while (!q.isEmpty()) {
            Process cur = q.poll();

            if (cur.priority == pq.peek()) {
                pq.poll();
                answer++;

                if (cur.index == location) {
                    return answer;
                }
            } else {
                q.offer(cur);
            }
        }

        return answer;
    }

    private class Process {
        int index;
        int priority;

        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
