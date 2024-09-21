package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {
    private static final int INF = 1_000_000_000;
    private static List<List<Node>> graph;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        distance = new int[V + 1]; // 시작점(K)로부터 각 노드까지의 최단거리 (ex: distance[1] = K부터 1번 노드까지의 최단거리)

        Arrays.fill(distance, INF);

        dijkstra(K);

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                answer.append("INF").append("\n");
            } else {
                answer.append(distance[i]).append("\n");
            }
        }

        System.out.println(answer);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int curIdx = poll.index;
            int curDist = poll.weight;

            if (distance[curIdx] < curDist) {
                continue;
            }

            for (Node adj : graph.get(curIdx)) {
                int adjIdx = adj.index;
                int adjDist = adj.weight;
                int newDist = distance[curIdx] + adjDist;

                if (newDist < distance[adjIdx]) {
                    distance[adjIdx] = newDist;
                    pq.offer(new Node(adjIdx, distance[adjIdx]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
