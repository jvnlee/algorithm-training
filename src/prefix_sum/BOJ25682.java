package prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25682 {
    public static int n, m, k;
    public static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = row.charAt(j - 1) == 'W';
            }
        }

        int[][] mismatchSumsW = getMismatchSums(true); // 첫 칸이 W인 체스판 기준, 잘못 칠해진 칸의 누적 개수 기록
        int[][] mismatchSumsB = getMismatchSums(false); // 첫 칸이 B인 체스판 기준, 잘못 칠해진 칸의 누적 개수 기록

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n - k + 1; i++) {
            for (int j = 1; j <= m - k + 1; j++) {
                int x1 = i;
                int y1 = j;

                int x2 = i + k - 1;
                int y2 = j + k - 1;

                int subSumW = mismatchSumsW[x2][y2] - mismatchSumsW[x2][y1 - 1] - mismatchSumsW[x1 - 1][y2] + mismatchSumsW[x1 - 1][y1 - 1];
                int subSumB = mismatchSumsB[x2][y2] - mismatchSumsB[x2][y1 - 1] - mismatchSumsB[x1 - 1][y2] + mismatchSumsB[x1 - 1][y1 - 1];

                answer = Math.min(answer, Math.min(subSumW, subSumB));
            }
        }

        System.out.println(answer);
    }

    public static int[][] getMismatchSums(boolean firstCellColor) {
        int[][] mismatchSums = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                boolean expectedColor = ((i + j) % 2 == 0) == firstCellColor;
                mismatchSums[i][j] = board[i][j] != expectedColor
                        ? mismatchSums[i - 1][j] + mismatchSums[i][j - 1] - mismatchSums[i - 1][j - 1] + 1
                        : mismatchSums[i - 1][j] + mismatchSums[i][j - 1] - mismatchSums[i - 1][j - 1];
            }
        }

        return mismatchSums;
    }
}
