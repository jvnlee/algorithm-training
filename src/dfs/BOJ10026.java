package dfs;

import java.util.*;

public class BOJ10026 {

    public static int n;
    public static char[][] image;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    public static int[] dy = {0, 0, -1, 1};
    public static int blindCount;
    public static int normalCount;

    public static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) continue;

            if (image[x][y] == image[nx][ny] && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();

        image = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("");
            for (int j = 0; j < n; j++) {
                image[i][j] = input[j].charAt(0);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    normalCount++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == 'G') image[i][j] = 'R';
            }
        }

        visited = new boolean[n][n]; // 방문 기록 초기화

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    blindCount++;
                }
            }
        }

        System.out.println(normalCount + " " + blindCount);
    }
}
