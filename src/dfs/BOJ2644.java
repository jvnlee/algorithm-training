package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class BOJ2644 {
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int p1, p2, count;
    public static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(br.readLine());
        checked = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        p1 = parseInt(st.nextToken());
        p2 = parseInt(st.nextToken());

        int m = parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = parseInt(st.nextToken());
            int child = parseInt(st.nextToken());

            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        for (List<Integer> g : graph) {
            System.out.println(g);
        }

        bfs(p1);

        if (count == 0) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    public static void bfs(int person) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(person);

        while (!queue.isEmpty()) {
            int p = queue.poll();

            if (p == p2) break;

            checked[p] = true;

            List<Integer> adj = graph.get(p);

            for (int i = 0; i < adj.size(); i++) {
                queue.offer(adj.get(i));
            }

            count++;
        }
    }
}
