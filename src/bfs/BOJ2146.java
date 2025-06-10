package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146 {
    private static int N;
    private static int islandNum = 1;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    islandNum++;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < islandNum; i++) {
            answer = Math.min(answer, bfs(i));
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = islandNum;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny);
            };
        }
    }

    private static int bfs(int startIslandNum) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == startIslandNum) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
                            q.offer(new int[]{i, j});
                            visited[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cx = poll[0];
            int cy = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] != 0 && map[nx][ny] != startIslandNum) {
                        return dist[cx][cy];
                    }

                    if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[cx][cy] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
