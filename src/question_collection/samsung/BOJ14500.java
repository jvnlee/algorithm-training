package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int answer;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;

                checkTShape(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    private static void checkTShape(int x, int y) {
        int[][] TShapes = {
                {-1, 0, 0, 1, 0, -1}, // ㅗ
                {0, 1, 1, 0, 0, -1}, // ㅜ
                {-1, 0, 1, 0, 0, -1}, // ㅓ
                {-1, 0, 0, 1, 1, 0}   // ㅏ
        };

        for (int[] shape : TShapes) {
            int sum = map[x][y];
            boolean valid = true;

            for (int i = 0; i < 3; i++) {
                int nx = x + shape[i * 2];
                int ny = y + shape[i * 2 + 1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    valid = false;
                    break;
                }

                sum += map[nx][ny];
            }

            if (valid) {
                answer = Math.max(answer, sum);
            }
        }
    }
}
