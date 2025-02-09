package programmers.high_score_kit.dfs_bfs.retries;

import java.util.*;

public class PRG43165_2 {
    private static int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        // bfs(numbers, target);
        return answer;
    }

    private void dfs(int[] numbers, int target, int currentSum, int idx) {
        if (idx == numbers.length) {
            if (currentSum == target) {
                answer++;
            }

            return;
        }

        dfs(numbers, target, currentSum + numbers[idx], idx + 1);
        dfs(numbers, target, currentSum - numbers[idx], idx + 1);
    }

    private void bfs(int[] numbers, int target) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] polled = q.poll();

            int currentSum = polled[0];
            int idx = polled[1];

            if (idx == numbers.length) {
                if (currentSum == target) {
                    answer++;
                }
            } else {
                q.offer(new int[]{currentSum + numbers[idx], idx + 1});
                q.offer(new int[]{currentSum - numbers[idx], idx + 1});
            }
        }
    }
}
