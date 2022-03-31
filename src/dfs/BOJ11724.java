package dfs;

import java.util.*;

public class BOJ11724 {

    public static int n, m;
    public static ArrayList<ArrayList<Integer>> graph;
    public static boolean[] marked;
    public static int answer;

    public static boolean dfs(int x) {
        if (marked[x]) {
            return false;
        } else {
            marked[x] = true;

            for (int i = 0; i < graph.get(x).size(); i++) {
                int adj = graph.get(x).get(i);
                if (!marked[adj]) {
                    dfs(adj);
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        graph = new ArrayList<>();
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

        for (int i = 0; i <= n; i++) {
            graph.get(i).sort(null);
        }

        for (int i = 1; i <= n; i++) {
            if (!marked[i]) {
                dfs(i);
                answer++;
            }
        }

        System.out.println(answer);
    }

}
