package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15685 {
    private static boolean[][] board = new boolean[100][100];
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            drawDragonCurve(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        int answer = 0;

        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int gen = 1; gen <= g; gen++) {
            for (int i = directions.size() - 1; i >= 0; i--) {
                int nd = (directions.get(i) + 1) % 4; // 기존 방향에서 90도 반시계방향
                directions.add(nd);
            }
        }

        board[y][x] = true;

        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            board[y][x] = true;
        }
    }
}
