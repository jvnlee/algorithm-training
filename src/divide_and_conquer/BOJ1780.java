package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {
    public static int[][] paper;
    public static int neg, zero, pos;

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

        System.out.println(neg + "\n" + zero + "\n" + pos);
    }

    private static void divideAndConquer(int startX, int startY, int size) {
        if (isSameNumber(startX, startY, size)) {
            int number = paper[startX][startY];
            if (number == -1) {
                neg++;
            } else if (number == 0) {
                zero++;
            } else {
                pos++;
            }
            return;
        }

        int dividedSize = size / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divideAndConquer(startX + i * dividedSize, startY + j * dividedSize, dividedSize);
            }
        }
    }

    private static boolean isSameNumber(int startX, int startY, int size) {
        int number = paper[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (number != paper[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
