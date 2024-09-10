package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] box = new int[h][n][m];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    box[i][j][k] = tomato;

                    if (tomato == 1) {
                        q.offer(new int[]{i, j, k});
                    }
                }
            }
        }

        int[] dz = {0, 0, 0, 0, -1, 1};
        int[] dx = {-1, 1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cz = poll[0];
            int cx = poll[1];
            int cy = poll[2];

            for (int i = 0; i < 6; i++) {
                int nz = cz + dz[i];
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nz >= 0 && nz < h && nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (box[nz][nx][ny] == 0) {
                        box[nz][nx][ny] = box[cz][cx][cy] + 1;
                        q.offer(new int[]{nz, nx, ny});
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }

                    answer = Math.max(answer, box[i][j][k]);
                }
            }
        }

        System.out.println(answer - 1);
    }
}
