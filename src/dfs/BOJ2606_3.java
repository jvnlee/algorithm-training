package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2606_3 {
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int connections = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= num; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 0; i < connections; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[num + 1];

        dfs(1);

        System.out.println(count - 1);
    }

    private static void dfs(int node) {
        visited[node] = true;
        count++;

        for (int adj : graph.get(node)) {
            if (!visited[adj]) {
                dfs(adj);
            }
        }
    }
}
