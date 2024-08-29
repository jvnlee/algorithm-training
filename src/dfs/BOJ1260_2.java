package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_2 {
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static final StringBuilder dfsRes = new StringBuilder();
    private static final StringBuilder bfsRes = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (List<Integer> edges : graph) {
            Collections.sort(edges);
        }

        visited = new boolean[n + 1];
        dfs(v);

        visited = new boolean[n + 1];
        bfs(v);

        System.out.println(dfsRes);
        System.out.println(bfsRes);
    }

    private static void dfs(int v) {
        visited[v] = true;
        dfsRes.append(v).append(" ");

        for (int adj : graph.get(v)) {
            if (!visited[adj]) {
                dfs(adj);
            }
        }
    }

    private static void bfs(int x) {
        Queue<Integer> q = new LinkedList<>();

        visited[x] = true;
        q.offer(x);

        while (!q.isEmpty()) {
            int cur = q.poll();
            bfsRes.append(cur).append(" ");

            for (int adj : graph.get(cur)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.offer(adj);
                }
            }
        }
    }
}
