package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_2 {
    private static int m, n;
    private static int[][] box;
    private static Queue<int[]> q;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                box[i][j] = tomato;

                if (tomato == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        bfs();

        System.out.println(getTotalDays());
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (box[nx][ny] == 0) {
                        box[nx][ny] = box[x][y] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    private static int getTotalDays() {
        int totalDays = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    return -1;
                }
                totalDays = Math.max(box[i][j], totalDays);
            }
        }

        return totalDays - 1;
    }
}
