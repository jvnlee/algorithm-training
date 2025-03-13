package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460 {
    private static int n, m;
    private static char[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    private static int bfs(int rx, int ry, int bx, int by) {
        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[rx][ry][bx][by] = true;

        Queue<TiltHistory> q = new LinkedList<>();
        q.offer(new TiltHistory(rx, ry, bx, by, 0));

        while (!q.isEmpty()) {
            TiltHistory th = q.poll();

            if (th.tiltCount >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int[] nr = move(th.rx, th.ry, dx[i], dy[i]);
                int[] nb = move(th.bx, th.by, dx[i], dy[i]);

                int nrx = nr[0];
                int nry = nr[1];
                int nrm = nr[2];

                int nbx = nb[0];
                int nby = nb[1];
                int nbm = nb[2];

                if (map[nbx][nby] == 'O') {
                    continue;
                }

                if (map[nrx][nry] == 'O') {
                    return th.tiltCount + 1;
                }

                if (nrx == nbx && nry == nby) {
                    if (nrm > nbm) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    q.offer(new TiltHistory(nrx, nry, nbx, nby, th.tiltCount + 1));
                }
            }
        }

        return -1;
    }

    private static int[] move(int x, int y, int dx, int dy) {
        int moveCount = 0;

        while (map[x + dx][y + dy] != '#' && map[x][y] != 'O') {
            x += dx;
            y += dy;
            moveCount++;
        }

        return new int[]{x, y, moveCount};
    }

    private static class TiltHistory {
        int rx, ry, bx, by, tiltCount;

        public TiltHistory(int rx, int ry, int bx, int by, int tiltCount) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.tiltCount = tiltCount;
        }
    }
}
