package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {
    private static int N;
    private static List<List<Edge>> graph;
    private static final int INF = 200_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int answer = Math.min(
                dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N),
                dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N)
        );

        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int dijkstra(int start, int end) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            int curTo = poll.to;
            int curDist = poll.distance;

            if (distance[curTo] < curDist) {
                continue;
            }

            for (Edge edge : graph.get(curTo)) {
                int adjTo = edge.to;
                int adjDist = edge.distance;

                if (distance[curTo] + adjDist < distance[adjTo]) {
                    distance[adjTo] = distance[curTo] + adjDist;
                    pq.offer(new Edge(adjTo, distance[adjTo]));
                }
            }
        }

        return distance[end];
    }

    private static class Edge implements Comparable<Edge> {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return distance - o.distance;
        }
    }
}
