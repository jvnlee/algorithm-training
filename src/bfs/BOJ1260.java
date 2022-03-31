package bfs;

import java.util.*;

public class BOJ1260 {

    public static int n, m, v;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static boolean[] marked;

    public static void dfs(int x) {
        marked[x] = true;
        System.out.print(x + " ");

        for (int i = 0; i < graph.get(x).size(); i++) {
            int adj = graph.get(x).get(i);

            if (!marked[adj]) {
                dfs(adj);
            }
        }
    }

    public static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();

        marked[x] = true;
        queue.offer(x);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < graph.get(node).size(); i++) {
                int adj = graph.get(node).get(i);
                if (!marked[adj]) {
                    marked[adj] = true;
                    queue.offer(adj);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 노드 개수
        m = scanner.nextInt(); // 엣지 개수
        v = scanner.nextInt(); // 시작 노드 번호

        marked = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for (int i = 1; i <= n; i++) {
            graph.get(i).sort(null);
        }

        dfs(v);
        System.out.println();
        marked = new boolean[n + 1];
        bfs(v);
    }
}
