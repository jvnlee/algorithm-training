package dfs;

import java.util.*;

public class BOJ2606 {

    public static int n, m, v;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static boolean[] infected;
    public static int count = 0;

    public static void dfs(int x) {
        infected[x] = true;

        for (int i = 0; i < graph.get(x).size(); i++) {
            int adj = graph.get(x).get(i);
            if (!infected[adj]) {
                infected[adj] = true;
                count++;
                dfs(adj);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 컴퓨터(노드)의 수
        m = scanner.nextInt(); // 연결(엣지)의 수

        infected = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        dfs(1);
        System.out.println(count);
    }
}
