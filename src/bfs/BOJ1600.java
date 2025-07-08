package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {
    private static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    private static int[] dy = {0, 0, -1, 1};

    private static int[] dxh = {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시 방향부터 시계 방향으로
    private static int[] dyh = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0, 0}); // 시작 좌표 x, y, 동작수, 말 동작수

        boolean[][][] visited = new boolean[H][W][K + 1];
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int cx = cur[0];
            int cy = cur[1];
            int totalMove = cur[2];
            int horseMove = cur[3];

            if (cx == H - 1 && cy == W - 1) {
                System.out.println(totalMove);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] != 1 && !visited[nx][ny][horseMove]) {
                    q.offer(new int[]{nx, ny, totalMove + 1, horseMove});
                    visited[nx][ny][horseMove] = true;
                }
            }

            if (horseMove < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = cx + dxh[i];
                    int ny = cy + dyh[i];

                    if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] != 1 && !visited[nx][ny][horseMove + 1]) {
                        q.offer(new int[]{nx, ny, totalMove + 1, horseMove + 1});
                        visited[nx][ny][horseMove + 1] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}