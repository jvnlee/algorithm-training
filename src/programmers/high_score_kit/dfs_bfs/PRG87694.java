package programmers.high_score_kit.dfs_bfs;

import java.util.*;

public class PRG87694 {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = new int[102][102]; // 좌표 범위 0 ~ 50 x2 스케일

        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        if (board[x][y] == 0) {
                            board[x][y] = 1; // 테두리
                        }
                    } else {
                        board[x][y] = 2; // 내부
                    }
                }
            }
        }

        Queue<Point> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        int startX = characterX * 2;
        int startY = characterY * 2;
        int targetX = itemX * 2;
        int targetY = itemY * 2;

        queue.offer(new Point(startX, startY, 0));
        visited.add(startX + ", " + startY);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cd = cur.dist;

            if (cx == targetX && cy == targetY) {
                return cd / 2; // 처음에 2배로 늘린 좌표이므로 나누기 2
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                String next = nx + ", " + ny;

                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && board[nx][ny] == 1 && !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(new Point(nx, ny, cd + 1));
                }
            }
        }

        return 0;
    }

    private class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
