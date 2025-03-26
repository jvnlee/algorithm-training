package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] A = new int[R][C];

        int clockX = 0;
        int counterClockX = 0;
        boolean isFound = false;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                int cell = Integer.parseInt(st.nextToken());

                if (cell == -1 && !isFound) {
                    counterClockX = i;
                    clockX = i + 1;
                    isFound = true;
                }

                A[i][j] = cell;
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int time = 0; time < T; time++) {
            int[][] tmp = new int[R][C];
            tmp[clockX][0] = -1;
            tmp[counterClockX][0] = -1;

            // 미세먼지 확산
            for (int x = 0; x < R; x++) {
                for (int y = 0; y < C; y++) {
                    if (A[x][y] > 0) {
                        int spreadAmount = A[x][y] / 5;
                        int dCount = 0;

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx >= 0 && nx < R && ny >= 0 && ny < C && A[nx][ny] != -1) {
                                tmp[nx][ny] += spreadAmount;
                                dCount++;
                            }
                        }

                        tmp[x][y] += A[x][y] - spreadAmount * dCount;
                    }
                }
            }

            A = tmp;

            // 공기청정기 작동 (상단, 반시계방향)
            for (int x = counterClockX - 1; x > 0; x--) {
                A[x][0] = A[x - 1][0];
            }

            for (int y = 0; y < C - 1; y++) {
                A[0][y] = A[0][y + 1];
            }

            for (int x = 0; x < counterClockX; x++) {
                A[x][C - 1] = A[x + 1][C - 1];
            }

            for (int y = C - 1; y > 1; y--) {
                A[counterClockX][y] = A[counterClockX][y - 1];
            }

            A[counterClockX][1] = 0;

            // 공기청정기 작동 (하단, 시계방향)
            for (int x = clockX + 1; x < R - 1; x++) {
                A[x][0] = A[x + 1][0];
            }

            for (int y = 0; y < C - 1; y++) {
                A[R - 1][y] = A[R - 1][y + 1];
            }

            for (int x = R - 1; x > clockX; x--) {
                A[x][C - 1] = A[x - 1][C - 1];
            }

            for (int y = C - 1; y > 1; y--) {
                A[clockX][y] = A[clockX][y - 1];
            }

            A[clockX][1] = 0;
        }

        int answer = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] > 0) {
                    answer += A[i][j];
                }
            }
        }

        System.out.println(answer);
    }
}
