package programmers.high_score_kit.stack_and_queue;

import java.util.*;

public class PRG12906 {
    public int[] solution(int[] arr) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                q.offer(arr[i]);
            }
        }

        int[] answer = new int[q.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = q.poll();
        }

        return answer;
    }
}
