package programmers;

public class PRG92344 {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;

        int[][] diff = new int[N + 1][M + 1];

        for (int[] s : skill) {
            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = type == 1 ? -s[5] : s[5];

            diff[x1][y1] += degree;
            diff[x1][y2 + 1] -= degree;
            diff[x2 + 1][y1] -= degree;
            diff[x2 + 1][y2 + 1] += degree;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += diff[i][j];

                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
