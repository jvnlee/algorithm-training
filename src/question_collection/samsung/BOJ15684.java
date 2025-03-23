package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    private static int N, M, H;
    private static boolean[][] map;
    private static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선의 개수
        M = Integer.parseInt(st.nextToken()); // 가로선의 개수
        H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 점선의 개수

        map = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 점선 번호
            int b = Integer.parseInt(st.nextToken()); // 세로선 번호

            map[a][b] = true;
        }

        // 1번 가로줄부터 시작해서 백트래킹으로 모든 사다리 배치 조합 시도
        dfs(0, 1);

        System.out.println(answer > 3 ? -1 : answer);
    }

    private static void dfs(int count, int row) {
        if (count >= answer) { // 지금까지 나온 정답보다 커지면 이 이상의 탐색은 불필요
            return;
        }

        if (isValid()) { // 현재 사다리 조합이 정답 조건 만족하는지 체크
            answer = count;
            return;
        }

        if (count == 3) { // 사다리 개수 최대값은 3개
            return;
        }

        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!map[i][j] && !map[i][j - 1] && !map[i][j + 1]) { // 현재 자리와 앞뒤에 사다리 없는지 체크
                    map[i][j] = true;
                    dfs(count + 1, i);
                    map[i][j] = false;
                }
            }
        }
    }

    private static boolean isValid() {
        for (int start = 1; start <= N; start++) {
            int col = start;

            for (int j = 1; j <= H; j++) {
                if (map[j][col]) {
                    col++;
                } else if (map[j][col - 1]) {
                    col--;
                }
            }

            if (col != start) {
                return false;
            }
        }

        return true;
    }
}
