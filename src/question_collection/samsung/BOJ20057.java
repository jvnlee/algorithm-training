package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    private static int N;
    private static int[][] map;
    private static int totalOutSand = 0;

    // ←, ↓, →, ↑
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    // 방향별 모래 흩날리는 상대 위치와 비율
    private static final int[][][] sandSpread = {
            // ←
            {{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}, {0, -1, -1}},
            // ↓
            {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}, {1, 0, -1}},
            // →
            {{-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}, {0, 1, -1}},
            // ↑
            {{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}, {-1, 0, -1}}
    };

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

        simulate();
        System.out.println(totalOutSand);
    }

    private static void simulate() {
        int x = N / 2;
        int y = N / 2;
        int dir = 0;
        int len = 1;

        while (true) {
            for (int repeat = 0; repeat < 2; repeat++) {
                for (int step = 0; step < len; step++) {
                    x += dx[dir];
                    y += dy[dir];
                    spreadSand(x, y, dir);
                    if (x == 0 && y == 0) return;
                }
                dir = (dir + 1) % 4;
            }
            len++;
        }
    }

    private static void spreadSand(int x, int y, int dir) {
        int sand = map[x][y];
        int remaining = sand;

        for (int[] s : sandSpread[dir]) {
            int nx = x + s[0];
            int ny = y + s[1];
            int percent = s[2];

            int amount;
            if (percent == -1) {
                amount = remaining;
            } else {
                amount = (sand * percent) / 100;
                remaining -= amount;
            }

            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                map[nx][ny] += amount;
            } else {
                totalOutSand += amount;
            }
        }

        map[x][y] = 0;
    }
}
