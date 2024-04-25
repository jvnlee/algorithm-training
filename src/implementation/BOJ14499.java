package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ14499 {
    public static int n, m, x, y;
    public static int[][] map;

    // 0번 인덱스는 안씀 (1 ~ 6만 사용)
    // 상단은 항상 1, 하단은 항상 6
    public static int[] dice = {0, 0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken()); // 지도 세로 크기
        m = parseInt(st.nextToken()); // 지도 가로 크기

        x = parseInt(st.nextToken()); // 주사위 x 좌표
        y = parseInt(st.nextToken()); // 주사위 y 좌표

        int k = parseInt(st.nextToken()); // 명령 개수

        map = new int[n][m];

        // 지도 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        // 명령 수행
        for (int i = 0; i < k; i++) {
            int dir = parseInt(st.nextToken());
            roll(dir, x, y);
        }
    }

    public static void roll(int dir, int curX, int curY) {
        if (!isValidDir(dir, curX, curY)) return;
        move(dir); // 주사위 이동

        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }

        // 주사위 상단 값 출력
        System.out.println(dice[1]);
    }

    public static boolean isValidDir(int dir, int curX, int curY) {
        if (dir == 1 && (curY + 1) >= m) return false;
        if (dir == 2 && (curY - 1) < 0) return false;
        if (dir == 3 && (curX - 1) < 0) return false;
        if (dir == 4 && (curX + 1) >= n) return false;
        return true;
    }

    public static void move(int dir) {
        int top = dice[1];
        if (dir == 1) {
            y++;
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = top;
        } else if (dir == 2) {
            y--;
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = top;
        } else if (dir == 3) {
            x--;
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = top;
        } else if (dir == 4) {
            x++;
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = top;
        }
    }
}
