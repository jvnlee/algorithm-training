package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427 {
    private static int w, h;
    private static char[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][] fireVisited;
    private static boolean[][] sanggeunVisited;
    private static Queue<int[]> sanggeunQueue;
    private static Queue<int[]> fireQueue;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fireVisited = new boolean[h][w];
            sanggeunVisited = new boolean[h][w];

            sanggeunQueue = new LinkedList<>();
            fireQueue = new LinkedList<>();

            for (int j = 0; j < h; j++) {
                String row = br.readLine();

                for (int k = 0; k < w; k++) {
                    map[j][k] = row.charAt(k);

                    if (map[j][k] == '@') {
                        sanggeunQueue.offer(new int[]{j, k, 0});
                    } else if (map[j][k] == '*') {
                        fireQueue.offer(new int[]{j, k});
                    }
                }
            }

            simulate();
        }

        System.out.println(sb);
    }

    private static void simulate() {
        while (!sanggeunQueue.isEmpty()) {
            int fireQueueSize = fireQueue.size();

            for (int x = 0; x < fireQueueSize; x++) {
                int[] cur = fireQueue.poll();
                int cx = cur[0];
                int cy = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (nx >= 0 && nx < h && ny >= 0 && ny < w && !fireVisited[nx][ny] && map[nx][ny] != '#') {
                        fireVisited[nx][ny] = true;
                        map[nx][ny] = '*';
                        fireQueue.offer(new int[]{nx, ny});
                    }
                }
            }

            int sanggeunQueueSize = sanggeunQueue.size();

            for (int x = 0; x < sanggeunQueueSize; x++) {
                int[] cur = sanggeunQueue.poll();
                int cx = cur[0];
                int cy = cur[1];
                int ct = cur[2];

                if (cx == 0 || cx == h - 1 || cy == 0 || cy == w - 1) {
                    sb.append(ct + 1).append("\n");
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (nx >= 0 && nx < h && ny >= 0 && ny < w && !sanggeunVisited[nx][ny] && map[nx][ny] == '.') {
                        sanggeunVisited[nx][ny] = true;
                        sanggeunQueue.offer(new int[]{nx, ny, ct + 1});
                    }
                }
            }
        }

        sb.append("IMPOSSIBLE").append("\n");
    }
}
