package question_collection.samsung;

import java.io.*;
import java.util.*;

public class BOJ19236 {
    static int maxScore = 0;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static class Fish {
        int x;
        int y;
        int d;

        public Fish (int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = num;
                fishes[num] = new Fish(i, j, dir);
            }
        }

        dfs(map, fishes, 0, 0, fishes[map[0][0]].d, 0);
        System.out.println(maxScore);
    }

    public static void dfs (int[][] map, Fish[] fishes, int x, int y, int d, int score) {
        score += map[x][y];
        maxScore = Math.max(maxScore, score);

        // 임시 배열 생성 후 복사
        int[][] copyMap = new int[4][4];
        Fish[] copyFish = new Fish[17];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 1; i < 17; i++) {
            copyFish[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].d);
        }

        // 상어가 물고기를 잡아먹은 경우, 물고기의 방향을 -1로 선언
        copyMap[x][y] = 0;
        copyFish[map[x][y]].d = -1;

        for (int i = 1; i < 17; i++) {
            Fish fish = copyFish[i];

            // 잡아먹힌 물고기의 경우
            if(fish.d == -1) {
                continue;
            }

            // 반시계 방향으로 돌면서 체크
            for (int k = 0; k < 8; k++) {
                int nx = fish.x + dx[(fish.d + k) % 8];
                int ny = fish.y + dy[(fish.d + k) % 8];

                if (nx < 0 || ny < 0 || nx > 3 || ny > 3) {
                    continue;
                }

                if (nx == x && ny == y) {
                    continue;
                }

                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = i;
                    copyMap[fish.x][fish.y] = 0;

                } else {
                    int to = copyMap[nx][ny];
                    copyMap[nx][ny] = i;
                    copyMap[fish.x][fish.y] = to;

                    copyFish[to].x = fish.x;
                    copyFish[to].y = fish.y;

                }

                fish.d = (fish.d + k) % 8;
                fish.x = nx;
                fish.y = ny;

                break;
            }
        }

        for (int step = 1; step <= 3; step++) {
            int nx = x + dx[d] * step;
            int ny = y + dy[d] * step;

            if (nx < 0 || ny < 0 || nx > 3 || ny > 3) {
                continue;
            }

            if (copyMap[nx][ny] == 0) {
                continue;
            }

            dfs(copyMap, copyFish, nx, ny, copyFish[copyMap[nx][ny]].d, score);
        }
    }
}