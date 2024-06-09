package algorithm_book;

import java.util.Scanner;

public class P149 {

    public static int n, m;
    public static int[][] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        sc.nextLine();

        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j)) cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static boolean dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) return false;

        if (graph[x][y] == 0) {
            graph[x][y] = 1;

            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);

            return true;
        }

        return false;
    }

}
