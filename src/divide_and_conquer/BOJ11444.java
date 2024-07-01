package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11444 {
    private static final int MOD = 1_000_000_007;
    private static final long[][] MATRIX = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long[][] poweredMatrix = divideAndConquer(MATRIX, n - 1);
        System.out.println(poweredMatrix[0][0]);
    }

    private static long[][] divideAndConquer(long[][] base, long exponent) {
        if (exponent == 0 || exponent == 1) {
            return base;
        }

        long[][] halfExponent = divideAndConquer(base, exponent / 2);

        return exponent % 2 == 0
                ? multiply(halfExponent, halfExponent)
                : multiply(multiply(halfExponent, halfExponent), base);
    }

    private static long[][] multiply(long[][] m1, long[][] m2) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
}
