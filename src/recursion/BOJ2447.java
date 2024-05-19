package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2447 {
    public static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        stars = new char[n][n];

        for (char[] row : stars) {
            Arrays.fill(row, '*');
        }

        applyPattern(0, 0, n, n);

        StringBuilder fractal = new StringBuilder();

        for (char[] row : stars) {
            fractal.append(
                    Arrays.toString(row)
                            .replaceAll("[\\[\\]]", "")
                            .replaceAll(", ", "")
            ).append("\n");
        }

        System.out.println(fractal);
    }

    private static void applyPattern(int startX, int startY, int endX, int endY) {
        int fractalLength = (endX - startX) / 3;

        for (int i = startX + fractalLength; i < (endX - fractalLength); i++) {
            for (int j = startY + fractalLength; j < (endY - fractalLength); j++) {
                stars[i][j] = ' ';
            }
        }

        if (fractalLength > 1) {
            for (int i = startX; i <= (endX - fractalLength); i += fractalLength) {
                for (int j = startY; j <= (endY - fractalLength); j += fractalLength) {
                    applyPattern(i, j, i + fractalLength, j + fractalLength);
                }
            }
        }
    }
}
