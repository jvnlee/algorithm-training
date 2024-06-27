package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
    public static int[][] paper;
    public static int white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideAndConquer(0, 0, n);

        System.out.println(white + "\n" + blue);
    }

    private static void divideAndConquer(int startX, int startY, int size) {
        if (isSameColor(startX, startY, size)) {
            if (paper[startX][startY] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }

        int dividedSize = size / 2;

        divideAndConquer(startX, startY, dividedSize);
        divideAndConquer(startX, startY + dividedSize, dividedSize);
        divideAndConquer(startX + dividedSize, startY, dividedSize);
        divideAndConquer(startX + dividedSize, startY + dividedSize, dividedSize);
    }

    private static boolean isSameColor(int startX, int startY, int size) {
        int color = paper[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (paper[i][j] != color) {
                    return false;
                }
            }
        }

        return true;
    }
}
