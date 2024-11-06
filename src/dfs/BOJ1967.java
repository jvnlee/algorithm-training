package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1967 {
    private static List<List<Edge>> graph;
    private static boolean[] visited;
    private static int answer = 0;
    private static int farthestNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        answer = 0;
        dfs(farthestNode, 0);

        System.out.println(answer);
    }

    private static void dfs(int node, int total) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        if (total > answer) {
            answer = total;
            farthestNode = node;
        }

        for (Edge edge : graph.get(node)) {
            dfs(edge.to, total + edge.weight);
        }
    }

    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
