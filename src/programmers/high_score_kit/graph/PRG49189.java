package programmers.high_score_kit.graph;

import java.util.*;

public class PRG49189 {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            int from = e[0];
            int to = e[1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int[] dist = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int polled = q.poll();

            for (int adj : graph.get(polled)) {
                if (!visited[adj]) {
                    dist[adj] = dist[polled] + 1;
                    visited[adj] = true;
                    q.offer(adj);
                }
            }
        }

        Arrays.sort(dist);

        int max = dist[n];

        for (int d : dist) {
            if (d == max) {
                answer++;
            }
        }

        return answer;
    }
}
