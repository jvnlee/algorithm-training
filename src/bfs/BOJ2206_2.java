package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][m + 1];
        int[][][] count = new int[n + 1][m + 1][2];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = input.charAt(j) - '0';
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1, 0});
        count[1][1][0] = 1;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cx = poll[0];
            int cy = poll[1];
            int chance = poll[2];

            if (cx == n && cy == m) {
                System.out.println(count[cx][cy][chance]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) {
                    continue;
                }

                if (map[nx][ny] == 0 && count[nx][ny][chance] == 0) {
                    count[nx][ny][chance] = count[cx][cy][chance] + 1;
                    q.offer(new int[]{nx, ny, chance});
                }

                if (map[nx][ny] == 1 && count[nx][ny][1] == 0 && chance == 0) {
                    count[nx][ny][1] = count[cx][cy][chance] + 1;
                    q.offer(new int[]{nx, ny, 1});
                }
            }
        }

        System.out.println(-1);
    }
}