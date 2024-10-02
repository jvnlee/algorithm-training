package programmers;

public class PRG72413 {
    private int[][] distance;
    private int INF = 40_000_000;
    private int nodes;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        nodes = n;

        distance = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    distance[i][j] = INF;
                }
            }
        }

        for (int[] f : fares) {
            int from = f[0];
            int to = f[1];
            int cost = f[2];

            distance[from][to] = cost;
            distance[to][from] = cost;
        }

        floydWarshall();

        int answer = distance[s][a] + distance[s][b];

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, distance[s][i] + distance[i][a] + distance[i][b]);
        }

        return answer;
    }

    private void floydWarshall() {
        for (int mid = 1; mid <= nodes; mid++) {
            for (int from = 1; from <= nodes; from++) {
                for (int to = 1; to <= nodes; to++) {
                    distance[from][to] = Math.min(distance[from][to], distance[from][mid] + distance[mid][to]);
                }
            }
        }
    }
}
