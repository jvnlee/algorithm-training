package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {
    private static int[] dx = {1, -1, 0, 0, 0, 0}; // 상 하 북 동 남 서
    private static int[] dy = {0, 0, -1, 0, 1, 0};
    private static int[] dz = {0, 0, 0, 1, 0, -1};

    private static int L, R, C;
    private static char[][][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            int sx = 0;
            int sy = 0;
            int sz = 0;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String level = br.readLine();

                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = level.charAt(k);

                        if (level.charAt(k) == 'S') {
                            sx = i;
                            sy = j;
                            sz = k;
                        }
                    }
                }

                br.readLine();
            }

            System.out.println(bfs(sx, sy, sz));
        }
    }

    private static String bfs(int sx, int sy, int sz) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sx, sy, sz, 0});
        visited[sx][sy][sz] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int cx = cur[0];
            int cy = cur[1];
            int cz = cur[2];
            int ct = cur[3];

            if (map[cx][cy][cz] == 'E') {
                return "Escaped in " + ct + " minute(s).";
            }

            for (int i = 0; i < 6; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nz = cz + dz[i];

                if (nx >= 0 && nx < L && ny >= 0 && ny < R && nz >= 0 && nz < C && map[nx][ny][nz] != '#' && !visited[nx][ny][nz]) {
                    q.offer(new int[]{nx, ny, nz, ct + 1});
                    visited[nx][ny][nz] = true;
                }
            }
        }

        return "Trapped!";
    }
}
