package programmers.high_score_kit.dfs_bfs;

import java.util.*;

public class PRG1844 {
    public int solution(int[][] maps) {
        return bfs(0, 0, maps); // 최단거리 문제는 BFS > DFS
    }

    private int bfs(int x, int y, int[][] maps) {
        int rows = maps.length;
        int cols = maps[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 1});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            int cx = polled[0];
            int cy = polled[1];
            int dist = polled[2];

            if (cx == rows - 1 && cy == cols - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length) {
                    if (maps[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return -1;
    }
}
