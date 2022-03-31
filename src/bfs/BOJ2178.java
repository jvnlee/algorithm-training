package bfs;

import java.util.*;

public class BOJ2178 {

    public static int n, m;
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    public static int[] dy = {0, 0, -1, 1};
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            x = node.getX();
            y = node.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;

                if (map[nx][ny] == 0) continue;

                if (map[nx][ny] == 1) {
                    map[nx][ny] = map[x][y] + 1;
                    queue.offer(new Node(nx, ny));
                }
            }
        }

        return map[n][m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        scanner.nextLine();

        map = new int[n + 1][m + 1];

        for (int i = 1; i < (n + 1); i++) {
            String line = scanner.nextLine();
            for (int j = 1; j < (m + 1); j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        System.out.println(bfs(1, 1));
    }

}
