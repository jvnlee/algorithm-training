package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {
    private static List<List<Integer>> graph;
    private static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < K; i++) {
            graph = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= V; j++) {
                graph.add(new ArrayList<>());
            }

            colors = new int[V + 1];

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            boolean isBipartite = false;

            for (int j = 1; j <= V; j++) {
                if (colors[j] == 0) {
                    isBipartite = bfs(j);
                }
                if (!isBipartite) {
                    break;
                }
            }

            if (isBipartite) {
                answer.append("YES\n");
            } else {
                answer.append("NO\n");
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        colors[v] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : graph.get(cur)) {
                if (colors[cur] == colors[adj]) {
                    return false;
                }

                if (colors[adj] == 0) {
                    colors[adj] = colors[cur] * -1;
                    q.offer(adj);
                }
            }
        }

        return true;
    }
}
