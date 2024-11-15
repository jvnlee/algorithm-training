package minimum_spanning_tree;

import java.io.*;
import java.util.*;

public class BOJ4386_Prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Star[] stars = new Star[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(x, y);
        }

        // 프림 알고리즘 적용
        double answer = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 0번 별부터 시작
        visited[0] = true;

        // 0번 별에서 뻗어나가는 간선 생성
        for (int i = 1; i < n; i++) {
            pq.offer(new Edge(i, stars[0].getDistance(stars[i])));
        }

        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount < n - 1) {
            Edge edge = pq.poll();

            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            answer += edge.weight;
            edgeCount++;

            // 새로 방문한 별에서 아직 방문하지 않은 나머지 별들로 뻗어나가는 간선 생성
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    pq.offer(new Edge(i, stars[edge.to].getDistance(stars[i])));
                }
            }
        }

        System.out.printf("%.2f%n", answer);
    }

    private static class Star {
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Star s) {
            return Math.sqrt(Math.pow(x - s.x, 2) + Math.pow(y - s.y, 2));
        }
    }

    private static class Edge implements Comparable<Edge> {
        int to;
        double weight;

        public Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }
    }
}
