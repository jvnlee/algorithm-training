package programmers.high_score_kit.graph.retries;

import java.util.*;

public class PRG49189_2 {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int v1 = e[0];
            int v2 = e[1];

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int[] distances = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : graph.get(cur)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    distances[adj] = distances[cur] + 1;
                    q.offer(adj);
                }
            }
        }

        Arrays.sort(distances);
        int max = distances[n];

        for (int d : distances) {
            if (d == max) {
                answer++;
            }
        }

        return answer;
    }
}