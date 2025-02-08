package programmers.high_score_kit.graph;

import java.util.*;

public class PRG49191 {
    // 풀이1: 그래프 + BFS
    public int solution(int n, int[][] results) {
        int answer = 0;

        List<List<Integer>> wins = new ArrayList<>();
        List<List<Integer>> loses = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            wins.add(new ArrayList<>());
            loses.add(new ArrayList<>());
        }

        for (int[] r : results) {
            int winner = r[0];
            int loser = r[1];

            wins.get(winner).add(loser);
            loses.get(loser).add(winner);
        }

        for (int i = 1; i <= n; i++) {
            int w = bfs(i, wins);
            int l = bfs(i, loses);

            if (w + l == n - 1) {
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
            int polled = q.poll();

            for (int adj : graph.get(polled)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    count++;
                    q.offer(adj);
                }
            }
        }

        return count;
    }

    // 풀이2: 인접 행렬 + 플로이드 와샬
    public int solution2(int n, int[][] results) {
        int answer = 0;

        boolean[][] resultMatrix = new boolean[n + 1][n + 1];

        for (int[] r : results) {
            resultMatrix[r[0]][r[1]] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && resultMatrix[i][k] && resultMatrix[k][j]) {
                        resultMatrix[i][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }

                if (resultMatrix[i][j] || resultMatrix[j][i]) {
                    count++;
                }
            }

            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
