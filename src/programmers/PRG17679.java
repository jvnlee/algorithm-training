package programmers;

public class PRG17679 {
    public int solution(int m, int n, String[] board) {
        String[] b = new String[n];
        boolean[][] mark = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(board[j].charAt(i));
            }
            b[i] = sb.toString();
        }

        boolean flag = true;

        while (flag) {
            flag = false;

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
                    char c = b[i].charAt(j);
                    if (c != ' ' && c == b[i].charAt(j + 1) && c == b[i + 1].charAt(j) && c == b[i + 1].charAt(j + 1)) {
                        mark[i][j] = true;
                        mark[i][j + 1] = true;
                        mark[i + 1][j] = true;
                        mark[i + 1][j + 1] = true;
                        flag = true;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < m; j++) {
                    if (mark[i][j]) continue;
                    sb.append(b[i].charAt(j));
                }

                String fs = String.format("%" + m + "s", sb);
                b[i] = fs;
            }

            mark = new boolean[n][m];
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (b[i].charAt(j) == ' ') answer++;
            }
        }

        return answer;
    }
}
