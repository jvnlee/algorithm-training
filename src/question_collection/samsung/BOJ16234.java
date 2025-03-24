package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16234 {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static List<int[]> group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        group = new ArrayList<>();
                        dfs(i, j);

                        if (group.size() > 1) {
                            flatten();
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) {
                break;
            }

            answer++;
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        group.add(new int[]{x, y});

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            int diff = Math.abs(map[x][y] - map[nx][ny]);

            if (diff >= L && diff <= R) {
                dfs(nx, ny);
            }
        }
    }

    private static void flatten() {
        int totalPopulation = 0;

        for (int[] c : group) {
            totalPopulation += map[c[0]][c[1]];
        }

        int avg = totalPopulation / group.size();

        for (int[] c : group) {
            map[c[0]][c[1]] = avg;
        }
    }
}
