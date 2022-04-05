package dfs;

import java.util.*;

public class BOJ4963 {
    public static int w, h, count;
    public static int[][] map;
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 북, 북동, 동, 남동, 남, 남서, 서, 북서 (대각선 방향이 포함됨)
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static StringBuilder sb = new StringBuilder();

    public static boolean dfs(int x, int y) {
        if (x < 0 || x >= h || y < 0 || y >= w) return false;
        if (map[x][y] == 0) return false;

        map[x][y] = 0;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(nx, ny);
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            w = scanner.nextInt();
            h = scanner.nextInt();
            count = 0;

            if (w == 0 && h == 0) break;

            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = scanner.nextInt();
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (dfs(i, j)) count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
