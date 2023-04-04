package programmers;

import java.util.*;

public class PRG1844 {
    public int solution(int[][] maps) {
        int maxX = maps.length;
        int maxY = maps[0].length;

        boolean[][] visited = new boolean[maxX][maxY];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Position> q = new LinkedList<>();

        q.offer(new Position(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Position p = q.poll();

            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue; // 맵 바깥인 경우

                if (maps[nx][ny] == 0) continue; // 벽인 경우

                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                maps[nx][ny] = maps[x][y] + 1;
                q.offer(new Position(nx, ny));
            }
        }

        if (maps[maxX - 1][maxY - 1] == 1) return -1;

        return maps[maxX - 1][maxY - 1];
    }

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
