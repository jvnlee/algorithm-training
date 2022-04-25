package dfs;

import java.util.*;

public class BOJ2468 {

    public static int n;
    public static int[][] map;
    public static int[] areaCounts;
    public static boolean[][] visited;

    public static int dfs(int x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= x) visited[i][j] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dfsdfs(i, j)) count++;
            }
        }

        if (count == 0) return 1;

        return count;
    }

    public static boolean dfsdfs(int x, int y) {
        if (x < 0 || x > n - 1 || y < 0 || y > n - 1) return false;

        if (!visited[x][y]) {
            visited[x][y] = true;

            dfsdfs(x - 1, y);
            dfsdfs(x + 1, y);
            dfsdfs(x, y - 1);
            dfsdfs(x, y + 1);

            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        int max = 1;
        int min = 100;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, Arrays.stream(map[i]).max().getAsInt());
            min = Math.min(min, Arrays.stream(map[i]).min().getAsInt());
        }

        int len = max - min + 1;

        areaCounts = new int[len];

        for (int i = min; i <= max; i++) {
            visited = new boolean[n][n];
            areaCounts[i - min] = dfs(i);
        }

        System.out.println(Arrays.stream(areaCounts).max().getAsInt());
    }
}
