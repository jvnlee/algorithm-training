package programmers.high_score_kit.heap;

import java.util.*;

public class PRG42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);

        PriorityQueue<int[]> waitQ = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);

        int idx = 0;
        int completedJobs = 0;
        int totalTime = 0;
        int currentTime = 0;

        while (completedJobs < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= currentTime) {
                waitQ.offer(jobs[idx]);
                idx++;
            }

            if (!waitQ.isEmpty()) {
                int[] job = waitQ.poll();
                completedJobs++;
                currentTime += job[1];
                totalTime += (currentTime - job[0]);
            } else {
                currentTime = jobs[idx][0];
            }
        }

        return totalTime / jobs.length;
    }
}
