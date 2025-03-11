package programmers.high_score_kit.graph.retries;

import java.util.*;

public class PRG49191_2 {
    public int solution(int n, int[][] results) {
        int answer = 0;

        List<List<Integer>> winGraph = new ArrayList<>();
        List<List<Integer>> loseGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            winGraph.add(new ArrayList<>());
            loseGraph.add(new ArrayList<>());
        }

        for (int[] r : results) {
            int winner = r[0];
            int loser = r[1];

            winGraph.get(winner).add(loser);
            loseGraph.get(loser).add(winner);
        }

        for (int i = 1; i <= n; i++) {
            int wins = bfs(i, winGraph);
            int loses = bfs(i, loseGraph);

            if (wins + loses == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private int bfs(int start, List<List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        int count = 0;

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : graph.get(cur)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.offer(adj);
                    count++;
                }
            }
        }

        return count;
    }
}
