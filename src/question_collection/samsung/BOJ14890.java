package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (canPass(i, true)) {
                answer++;
            }

            if (canPass(i, false)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean canPass(int idx, boolean isRow) {
        boolean[] slope = new boolean[N];
        int[] line = new int[N];

        for (int i = 0; i < N; i++) {
            line[i] = isRow ? map[idx][i] : map[i][idx];
        }

        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) {
                continue;
            }

            if (Math.abs(line[i] - line[i + 1]) > 1) {
                return false;
            }

            if (line[i] < line[i + 1]) { // 오르막 경사로 설치
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || line[i - j] != line[i] || slope[i - j]) {
                        // 경사로 설치할 칸이 맵 밖으로 벗어나거나, 현재 칸과 높이가 다르거나, 해당 칸에 이미 경사로가 있는 경우는 불가
                        return false;
                    }

                    slope[i - j] = true;
                }
            } else { // 내리막 경사로 설치
                for (int j = 0; j < L; j++) {
                    if (i + 1 + j >= N || line[i + 1 + j] != line[i + 1] || slope[i + 1 + j]) {
                        return false;
                    }

                    slope[i + 1 + j] = true;
                }
            }
        }

        return true;
    }
}
