package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점이 더 많은 경우에는 정점 중심의 프림, 간선이 더 많은 경우에는 간선 중심의 크루스칼 알고리즘을 사용하는 것이 유리함
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>(); // 크루스칼 풀이

        parents = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(A, B, C));
        }

        long answer = 0;
        int edgeCount = 0;

        while (!edges.isEmpty() && edgeCount < V - 1) {
            Edge edge = edges.poll();

            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;

            if (find(from) != find(to)) {
                union(from, to);
                answer += weight;
            }
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return;
        }

        if (ra < rb) {
            parents[rb] = ra;
        } else {
            parents[ra] = rb;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(weight, e.weight);
        }
    }
}
