package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11657 {
    private static int N;
    private static int INF = 100_000_000;
    private static List<Edge> edges = new ArrayList<>();
    private static long[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.add(new Edge(A, B, C));
        }

        distance = new long[N + 1];
        Arrays.fill(distance, INF);

        boolean hasNegativeCycle = bellmanFord(1);

        if (hasNegativeCycle) {
            System.out.println(-1);
        } else {
            StringBuilder answer = new StringBuilder();

            for (int i = 2; i <= N; i++) {
                long dist = distance[i];

                if (dist == INF) {
                    answer.append("-1").append("\n");
                } else {
                    answer.append(dist).append("\n");
                }
            }

            System.out.println(answer);
        }
    }

    private static boolean bellmanFord(int start) {
        distance[start] = 0;

        // N-1번의 사이클 수행
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                int from = edge.from;
                int to = edge.to;
                int dist = edge.distance;

                if (distance[from] != INF && distance[from] + dist < distance[to]) {
                    distance[to] = distance[from] + dist;
                }
            }
        }

        // N번째 사이클에서 음수 간선 순환 검사
        for (Edge edge : edges) {
            int from = edge.from;
            int to = edge.to;
            int dist = edge.distance;

            if (distance[from] != INF && distance[from] + dist < distance[to]) {
                return true;
            }
        }

        return false;
    }

    private static class Edge {
        int from;
        int to;
        int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
}
