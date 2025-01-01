package programmers.high_score_kit.greedy;

import java.util.*;

public class PRG42861 {
    private static int[] parents;

    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int[] c : costs) {
            edges.offer(new Edge(c[0], c[1], c[2]));
        }

        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int edgeCount = 0;
        int totalWeight = 0;

        while (!edges.isEmpty() && edgeCount < n - 1) {
            Edge e = edges.poll();

            int from = e.from;
            int to = e.to;
            int weight = e.weight;

            if (find(from) != find(to)) {
                union(from, to);
                edgeCount++;
                totalWeight += weight;
            }
        }

        return totalWeight;
    }

    private void union(int n1, int n2) {
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

    private int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    private class Edge implements Comparable<Edge> {
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
            return Integer.compare(this.weight, e.weight);
        }
    }
}
