package programmers.high_score_kit.dfs_bfs;

import java.util.*;

public class PRG43165 {
    private static int answer = 0;
    private static int[] nums;
    private static int targetSum;

    public int solution(int[] numbers, int target) {
        nums = numbers;
        targetSum = target;

        dfs(0, 0);
        // bfs();

        return answer;
    }

    private void dfs(int currentSum, int idx) {
        if (idx == nums.length) {
            if (currentSum == targetSum) {
                answer++;
            }

            return;
        }

        dfs(currentSum + nums[idx], idx + 1);
        dfs(currentSum - nums[idx], idx + 1);
    }

    private void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            int currentSum = polled[0];
            int idx = polled[1];

            if (idx == nums.length) {
                if (currentSum == targetSum) {
                    answer++;
                }
            } else {
                queue.offer(new int[]{currentSum + nums[idx], idx + 1});
                queue.offer(new int[]{currentSum - nums[idx], idx + 1});
            }
        }
    }
}
