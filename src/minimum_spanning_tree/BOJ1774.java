package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1774 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to); // 이미 연결된 간선은 유니온으로 합침
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = nodes[i].getDistance(nodes[j]);
                edges.offer(new Edge(i, j, dist)); // 모든 노드 사이에 간선을 생성
            }
        }

        // 크루스칼 알고리즘 적용
        double answer = 0;
        int edgeCount = 0;

        while (!edges.isEmpty() && edgeCount < N - 1) {
            Edge edge = edges.poll();

            int from = edge.from;
            int to = edge.to;
            double dist = edge.dist;

            if (find(from) != find(to)) {
                union(from, to);
                answer += dist;
                edgeCount++;
            }
        }

        System.out.printf("%.2f", answer);
    }

    private static void union(int n1, int n2) {
        int r1 = find(n1);
        int r2 = find(n2);

        if (r1 == r2) {
            return;
        }

        if (r1 < r2) {
            parents[r2] = r1;
        } else {
            parents[r1] = r2;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Node n) {
            return Math.sqrt(Math.pow(n.x - x, 2) + Math.pow(n.y - y, 2));
        }
    }

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double dist;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.dist, e.dist);
        }
    }
}
