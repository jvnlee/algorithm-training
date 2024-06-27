package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    public static int[][] video;
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        video = new int[n][n];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                video[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        divideAndConquer(0, 0, n);

        System.out.println(answer);
    }

    private static void divideAndConquer(int startX, int startY, int size) {
        if (isSameColor(startX, startY, size)) {
            answer.append(video[startX][startY]);
            return;
        }

        answer.append("(");

        int dividedSize = size / 2;

        divideAndConquer(startX, startY, dividedSize);
        divideAndConquer(startX, startY + dividedSize, dividedSize);
        divideAndConquer(startX + dividedSize, startY, dividedSize);
        divideAndConquer(startX + dividedSize, startY + dividedSize, dividedSize);

        answer.append(")");
    }

    private static boolean isSameColor(int startX, int startY, int size) {
        int color = video[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (video[i][j] != color) {
                    return false;
                }
            }
        }

        return true;
    }
}
