package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] fireVisited = new boolean[R][C];
        boolean[][] jihunVisited = new boolean[R][C];

        Queue<int[]> fireQueue = new LinkedList<>();
        Queue<int[]> jihunQueue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String row = br.readLine();

            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);

                if (map[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j});
                    fireVisited[i][j] = true;
                } else if (map[i][j] == 'J') {
                    jihunQueue.offer(new int[]{i, j, 0});
                    jihunVisited[i][j] = true;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!jihunQueue.isEmpty()) {
            int fireQueueSize = fireQueue.size();

            for (int i = 0; i < fireQueueSize; i++) {
                int[] cur = fireQueue.poll();
                int cx = cur[0];
                int cy = cur[1];

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && !fireVisited[nx][ny] && map[nx][ny] != '#') {
                        fireVisited[nx][ny] = true;
                        map[nx][ny] = 'F';
                        fireQueue.offer(new int[]{nx, ny});
                    }
                }
            }

            int jihunQueueSize = jihunQueue.size();

            for (int i = 0; i < jihunQueueSize; i++) {
                int[] cur = jihunQueue.poll();
                int cx = cur[0];
                int cy = cur[1];
                int ct = cur[2];

                if (cx == 0 || cx == R - 1 || cy == 0 || cy == C - 1) {
                    System.out.println(ct + 1);
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && !jihunVisited[nx][ny] && map[nx][ny] == '.') {
                        jihunVisited[nx][ny] = true;
                        jihunQueue.offer(new int[]{nx, ny, ct + 1});
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
