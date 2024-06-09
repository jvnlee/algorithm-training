package programmers_course.w4;

import java.util.*;

public class W4_1 {
    public int[][] solution(int rows, int columns, int max_virus, int[][] queries) {
        int[][] map = new int[rows][columns];

        Queue<Location> queue = new LinkedList<>();

        for (int[] query : queries) {
            boolean[][] visited = new boolean[rows][columns];

            queue.offer(new Location(query[0] - 1, query[1] - 1));

            while(!queue.isEmpty()) {
                Location l = queue.poll();

                int x = l.x;
                int y = l.y;

                if (x < 0 || y < 0 || x >= rows || y >= columns) continue;
                if (visited[x][y]) continue;

                visited[x][y] = true;

                if (map[x][y] < max_virus) {
                    map[x][y]++;
                    continue;
                }

                queue.offer(new Location(x - 1, y));
                queue.offer(new Location(x + 1, y));
                queue.offer(new Location(x, y - 1));
                queue.offer(new Location(x, y + 1));
            }
        }

        return map;
    }

    private class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
