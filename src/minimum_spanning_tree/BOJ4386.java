package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        // 좌표를 받아 모든 별을 저장
        Star[] stars = new Star[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            stars[i] = new Star(
                    Double.parseDouble(st.nextToken()),
                    Double.parseDouble(st.nextToken())
            );
        }

        // 모든 별 사이의 간선을 만들어 저장
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = stars[i].getDistance(stars[j]);
                edges.offer(new Edge(i, j, dist));
            }
        }

        // 크루스칼 알고리즘 적용
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        double answer = 0;
        int edgeCount = 0;

        while (!edges.isEmpty() && edgeCount < n - 1) {
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

        System.out.printf("%.2f%n", answer);
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

    private static int find(int n) {
        if (parents[n] == n) {
            return n;
        }

        return parents[n] = find(parents[n]);
    }

    private static class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Star s) {
            double sx = s.x;
            double sy = s.y;

            return Math.sqrt(Math.pow(x - s.x, 2) + Math.pow(y - s.y, 2));
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
