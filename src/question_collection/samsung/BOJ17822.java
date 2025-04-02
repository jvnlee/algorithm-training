package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822 {
    private static int N, M, T;
    private static int[][] plates;
    private static boolean[][] visited;
    private static boolean hasRemoved;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        plates = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                plates[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);

            visited = new boolean[N + 1][M];
            hasRemoved = false;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && plates[i][j] != 0) {
                        bfs(i, j);
                    }
                }
            }

            if (!hasRemoved) {
                adjust();
            }
        }

        int sum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += plates[i][j];
            }
        }

        System.out.println(sum);
    }

    private static void rotate(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            int[] tmp = new int[M];

            for (int j = 0; j < M; j++) {
                if (d == 0) {
                    tmp[(j + k) % M] = plates[i][j];
                } else {
                    tmp[(j - k + M) % M] = plates[i][j];
                }
            }

            plates[i] = tmp;
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        visited[x][y] = true;

        List<int[]> removeList = new ArrayList<>();
        removeList.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = (cy + dy[i] + M) % M;

                if (nx >= 1 && nx <= N && !visited[nx][ny] && plates[nx][ny] == plates[x][y]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    removeList.add(new int[]{nx, ny});
                }
            }
        }

        if (removeList.size() > 1) {
            hasRemoved = true;

            for (int[] r : removeList) {
                plates[r[0]][r[1]] = 0;
            }
        }
    }

    private static void adjust() {
        int sum = 0;
        int count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (plates[i][j] != 0) {
                    sum += plates[i][j];
                    count++;
                }
            }
        }

        if (count == 0) {
            return;
        }

        double avg = (double) sum / count;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (plates[i][j] != 0) {
                    if (plates[i][j] > avg) {
                        plates[i][j]--;
                    } else if (plates[i][j] < avg){
                        plates[i][j]++;
                    }
                }
            }
        }
    }
}
