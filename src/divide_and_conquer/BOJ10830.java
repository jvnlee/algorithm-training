package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830 {
    private static final int MOD = 1000;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = divideAndConquer(matrix, b);

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                answer.append(result[i][j] % MOD).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static int[][] divideAndConquer(int[][] m, long exponent) {
        if (exponent == 1) {
            return m;
        }

        int[][] halfExponent = divideAndConquer(m, exponent / 2);

        return exponent % 2 == 0
                ? multiply(halfExponent, halfExponent)
                : multiply(multiply(halfExponent, halfExponent), m);
    }

    private static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    matrix[i][j] += m1[i][k] * m2[k][j];
                    matrix[i][j] %= MOD;
                }
            }
        }

        return matrix;
    }
}
