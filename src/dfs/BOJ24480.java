package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ24480 {
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int[] order;
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (List<Integer> edges : graph) {
            edges.sort(Collections.reverseOrder());
        }

        visited = new boolean[n + 1];
        order = new int[n + 1];

        dfs(r);

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            answer.append(order[i]).append("\n");
        }

        System.out.println(answer);
    }

    private static void dfs(int x) {
        visited[x] = true;
        order[x] = count++;

        for (int v : graph.get(x)) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
}
