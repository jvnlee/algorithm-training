package programmers.high_score_kit.heap;

import java.util.*;

public class PRG42626 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.offer(s);
        }

        int answer = 0;

        while (pq.peek() < K && pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            int mixed = first + (second * 2);

            pq.offer(mixed);
            answer++;
        }

        if (pq.size() == 1 && pq.peek() < K) {
            return -1;
        }

        return answer;
    }
}
