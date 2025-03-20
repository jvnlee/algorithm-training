package question_collection.samsung;

import java.io.*;
import java.util.*;

public class BOJ15683 {
    private static int N, M;
    private static int minBlindSpot = Integer.MAX_VALUE;
    private static int[][] map;
    private static List<int[]> cctvs = new ArrayList<>();
    private static int[][][] directions = {
            {}, // 0번은 없음
            {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}, // 1번 CCTV (4방향)
            {{0, 1, 0, -1}, {1, 0, -1, 0}},    // 2번 CCTV (2방향)
            {{-1, 0, 0, 1}, {0, 1, 1, 0}, {1, 0, 0, -1}, {0, -1, -1, 0}}, // 3번 CCTV (4방향)
            {{-1, 0, 0, 1, 0, -1}, {0, 1, 1, 0, -1, 0}, {1, 0, 0, -1, 0, 1}, {0, -1, -1, 0, 1, 0}}, // 4번 CCTV (4방향)
            {{-1, 0, 1, 0, 0, -1, 0, 1}} // 5번 CCTV (1방향)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, map[i][j]});
                }
            }
        }

        dfs(0);
        System.out.println(minBlindSpot);
    }

    private static void dfs(int depth) {
        if (depth == cctvs.size()) {
            minBlindSpot = Math.min(minBlindSpot, countBlindSpot());
            return;
        }

        int[] cctv = cctvs.get(depth);
        int x = cctv[0], y = cctv[1], type = cctv[2];

        for (int[] dir : directions[type]) {
            int[][] mapCopy = new int[N][M];

            for (int i = 0; i < N; i++) {
                mapCopy[i] = map[i].clone();
            }

            mark(x, y, dir);
            dfs(depth + 1);
            map = mapCopy;
        }
    }

    private static void mark(int x, int y, int[] dir) {
        for (int i = 0; i < dir.length; i += 2) {
            int dx = dir[i], dy = dir[i + 1];
            int nx = x;
            int ny = y;

            while (true) {
                nx += dx;
                ny += dy;

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) {
                    break;
                }

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 7; // 감시되는 영역은 7로 표시
                }
            }
        }
    }

    private static int countBlindSpot() {
        int count = 0;

        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 0) count++;
            }
        }

        return count;
    }
}
