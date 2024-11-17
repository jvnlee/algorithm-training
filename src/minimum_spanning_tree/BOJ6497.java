package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ6497 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            if (input.equals("0 0")) {
                break;
            }

            st = new StringTokenizer(input);

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            parents = new int[m];

            for (int i = 0; i < m; i++) {
                parents[i] = i;
            }

            PriorityQueue<Edge> edges = new PriorityQueue<>();

            int total = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                total += z;

                edges.offer(new Edge(x, y, z));
            }

            int used = 0;
            int edgeCount = 0;

            while (!edges.isEmpty() && edgeCount < m - 1) {
                Edge edge = edges.poll();

                int from = edge.from;
                int to = edge.to;
                int dist = edge.dist;

                if (find(from) != find(to)) {
                    union(from, to);
                    edgeCount++;
                    used += dist;
                }
            }

            bw.write(total - used + "\n");
        }

        bw.flush();
        bw.close();
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

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.dist, e.dist);
        }
    }
}
