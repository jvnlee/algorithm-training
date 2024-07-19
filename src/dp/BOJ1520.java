package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    private static int m, n;
    private static int[][] map, dp;
    private static int[] dx = new int[]{-1, 1, 0, 0}; // 상 하 좌 우
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m + 1][n + 1];
        dp = new int[m + 1][n + 1]; // dp[a][b]: (a, b)부터 (m, n)까지 가능한 경로의 개수

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1, 1));
    }

    private static int dfs(int x, int y) {
        if (x == m && y == n) { // 종착점 (m, n)에 도달한 경우, 1가지의 경로로 인정
            return 1;
        }

        if (dp[x][y] != -1) { // 이미 다녀간 칸인 경우, 저장해놓은 값 사용
            return dp[x][y];
        }

        dp[x][y] = 0; // 방문 처리

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 1 && nx <= m && ny >= 1 && ny <= n) {
                if (map[nx][ny] < map[x][y]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }

        return dp[x][y];
    }
}
