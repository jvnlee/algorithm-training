package bfs;

import java.util.*;

public class BOJ2206 {

    public static int n, m;
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static boolean[][][] visited;

    public static class Node {
        int x;
        int y;
        int count;
        boolean wallCrush;

        public Node(int x, int y, int count, boolean wallCrush) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.wallCrush = wallCrush;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, 1, false));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            // 현재 처리할 노드가 도착지일 때 카운트 반환 후 종료
            if (node.x == (n - 1) && node.y == (m - 1)) {
                return node.count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 벽이 아닐 때
                if (map[nx][ny] == 0) {
                    // 현재까지 벽을 부수지 않았고, 벽 안부순 visited 배열에 방문 기록이 없을 때
                    if (!node.wallCrush && !visited[nx][ny][0]) {
                        queue.offer(new Node(nx, ny, node.count + 1, false));
                        visited[nx][ny][0] = true;
                    }

                    // 현재까지 벽을 1번 부쉈고, 벽 부순 visited 배열에 방문 기록이 없을 때
                    if (node.wallCrush && !visited[nx][ny][1]) {
                        queue.offer(new Node(nx, ny, node.count + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }

                // 벽일 때
                if (map[nx][ny] == 1) {
                    // 현재까지 벽을 부수지 않았을 때
                    if (!node.wallCrush) {
                        queue.offer(new Node(nx, ny, node.count + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }

        // 도착지에 도달하지 못하고 queue를 모두 소모해 while문을 빠져나온 경우 -1 반환
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        scanner.nextLine();

        map = new int[n][m];

        // visited[n][m][0]: 벽을 한번도 부수지 않고 탐색했을 때의 방문 기록
        // visited[n][m][1]: 벽을 한번 부수고 나서 탐색했을 때의 방문 기록
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));;
    }
}
