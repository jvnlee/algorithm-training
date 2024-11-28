package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {
    private static int n;
    private static int[][] map;
    private static int[][] dp;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n]; // dp[i][j]: (i, j)에서 출발해서 가능한 모든 지점을 방문했을 때, 방문한 총 칸의 개수

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, explore(i, j));
            }
        }

        System.out.println(answer);
    }

    private static int explore(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], explore(nx, ny) + 1); // 후자: 이웃 칸에서 탐색을 시작하여 방문한 총 칸의 수 + 1(현재 칸 포함시키기)
            }
        }

        return dp[x][y];
    }
}
