package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] table = new int[N][3];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dpMax = new int[N][3];

        for (int i = 0; i < 3; i++) {
            dpMax[0][i] = table[0][i];
        }

        for (int i = 1; i < N; i++) {
            int first = dpMax[i - 1][0];
            int second = dpMax[i - 1][1];
            int third = dpMax[i - 1][2];

            dpMax[i][0] = Math.max(first, second) + table[i][0];
            dpMax[i][1] = Math.max(first, Math.max(second, third)) + table[i][1];
            dpMax[i][2] = Math.max(second, third) + table[i][2];
        }

        StringBuilder answer = new StringBuilder();

        Arrays.sort(dpMax[N - 1]);
        answer.append(dpMax[N - 1][2]).append(" ");

        int[][] dpMin = new int[N][3];

        for (int i = 0; i < 3; i++) {
            dpMin[0][i] = table[0][i];
        }

        for (int i = 1; i < N; i++) {
            int first = dpMin[i - 1][0];
            int second = dpMin[i - 1][1];
            int third = dpMin[i - 1][2];

            dpMin[i][0] = Math.min(first, second) + table[i][0];
            dpMin[i][1] = Math.min(first, Math.min(second, third)) + table[i][1];
            dpMin[i][2] = Math.min(second, third) + table[i][2];
        }

        Arrays.sort(dpMin[N - 1]);
        answer.append(dpMin[N - 1][0]);

        System.out.println(answer);
    }
}
