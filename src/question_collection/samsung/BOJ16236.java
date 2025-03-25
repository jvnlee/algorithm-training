package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    private static int N, sharkX, sharkY;
    private static int sharkSize = 2;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int cell = Integer.parseInt(st.nextToken());

                if (cell == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                } else {
                    map[i][j] = cell;
                }
            }
        }

        int answer = 0;
        int eatCount = 0;

        while (true) {
            Fish target = bfs();

            if (target == null) {
                System.out.println(answer);
                return;
            }

            answer += target.dist;
            eatCount++;
            map[target.x][target.y] = 0;
            sharkX = target.x;
            sharkY = target.y;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }
    }

    private static Fish bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[]{sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;

        PriorityQueue<Fish> availableFish = new PriorityQueue<>();

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cx = poll[0];
            int cy = poll[1];
            int cd = poll[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;

                    if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                        availableFish.offer(new Fish(nx, ny, cd + 1));
                    }

                    q.offer(new int[]{nx, ny, cd + 1});
                }
            }
        }

        return availableFish.isEmpty() ? null : availableFish.poll();
    }

    private static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish f) {
            if (this.dist == f.dist) {
                if (this.x == f.x) {
                    return this.y - f.y;
                } else {
                    return this.x - f.x;
                }
            } else {
                return this.dist - f.dist;
            }
        }
    }
}
