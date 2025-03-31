package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17779 {
    private static int N;
    private static int[][] A;
    private static int totalPopulation = 0;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                totalPopulation += A[i][j];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N) {
                            divideAndCount(x, y, d1, d2);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void divideAndCount(int x, int y, int d1, int d2) {
        boolean[][] borders = new boolean[N + 1][N + 1];

        for (int i = 0; i <= d1; i++) {
            borders[x + i][y - i] = true;
            borders[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            borders[x + i][y + i] = true;
            borders[x + d1 + i][y - d1 + i] = true;
        }

        int[] populations = new int[6];

        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (borders[i][j]) {
                    break;
                }

                populations[1] += A[i][j];
            }
        }

        for (int i = 1; i <= x + d2; i++) {
            for (int j = N; j > y; j--) {
                if (borders[i][j]) {
                    break;
                }

                populations[2] += A[i][j];
            }
        }

        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (borders[i][j]) {
                    break;
                }

                populations[3] += A[i][j];
            }
        }

        for (int i = x + d2 + 1; i <= N; i++) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (borders[i][j]) {
                    break;
                }

                populations[4] += A[i][j];
            }
        }

        populations[5] = totalPopulation;

        for (int i = 1; i < 5; i++) {
            populations[5] -= populations[i];
        }

        Arrays.sort(populations);

        answer = Math.min(answer, populations[5] - populations[1]);
    }
}
