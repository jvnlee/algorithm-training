package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1167 {
    private static List<List<Edge>> graph;
    private static int answer = 0;
    private static int farthestNode = 0;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Edge(to, dist));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0); // 아무 노드를 하나 잡고 가장 먼 노드 탐색

        visited = new boolean[V + 1];
        answer = 0;
        dfs(farthestNode, 0); // 해당 노드로부터 가장 먼 노드 탐색

        System.out.println(answer);
    }

    private static void dfs(int node, int dist) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        if (dist > answer) {
            answer = dist;
            farthestNode = node;
        }

        for (Edge edge : graph.get(node)) {
            dfs(edge.to, dist + edge.dist);
        }
    }

    private static class Edge {
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
