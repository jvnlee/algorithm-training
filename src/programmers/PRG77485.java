package programmers;

public class PRG77485 {
    public int[][] matrix;

    public int[] solution(int rows, int columns, int[][] queries) {
        matrix = new int[rows][columns];
        int k = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = k;
                k++;
            }
        }

        int[] answer = new int[queries.length];
        int i = 0;

        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;

            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            int min = rotate(x1, y1, x2, y2);
            answer[i] = min;
            i++;
        }

        return answer;
    }

    public int rotate(int x1, int y1, int x2, int y2) {
        int min = 10000;

        int tmp1 = matrix[x1][y2];
        int tmp2 = matrix[x2][y2];
        int tmp3 = matrix[x2][y1];

        // 위 테두리
        for (int j = y2; j > y1; j--) {
            matrix[x1][j] = matrix[x1][j - 1];
            min = Math.min(min, matrix[x1][j]);
        }

        // 오른쪽 테두리
        for (int i = x2; i > x1; i--) {
            if (i == x1 + 1) {
                matrix[i][y2] = tmp1;
            } else {
                matrix[i][y2] = matrix[i - 1][y2];
            }
            min = Math.min(min, matrix[i][y2]);
        }

        // 아래 테두리
        for (int j = y1; j < y2; j++) {
            if (j == y2 - 1) {
                matrix[x2][j] = tmp2;
            } else {
                matrix[x2][j] = matrix[x2][j + 1];
            }
            min = Math.min(min, matrix[x2][j]);
        }

        // 왼쪽 테두리
        for (int i = x1; i < x2; i++) {
            if (i == x2 - 1) {
                matrix[i][y1] = tmp3;
            } else {
                matrix[i][y1] = matrix[i + 1][y1];
            }
            min = Math.min(min, matrix[i][y1]);
        }

        return min;
    }
}
