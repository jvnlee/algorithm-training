package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ14503 {
    public static int[][] map;
    public static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서 한칸 이동 시 x좌표 변화
    public static int[] dy = {0, 1, 0, -1}; // 북, 동, 남, 서 한칸 이동 시 y좌표 변화
    public static int n, m, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken()); // 행
        m = parseInt(st.nextToken()); // 열

        st = new StringTokenizer(br.readLine());
        int r = parseInt(st.nextToken()); // 초기 x 좌표
        int c = parseInt(st.nextToken()); // 초기 y 좌표
        int d = parseInt(st.nextToken()); // 초기 방향 (북, 동, 남, 서)

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 초기 위치부터 청소하고 시작
        count = 1;
        map[r][c] = 2; // 청소한 칸은 2로 표시

        clean(r, c, d);

        System.out.println(count);
    }

    public static void clean(int x, int y, int d) {
        // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인하고
        // 있다면 3번 조건 수행
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 반시계방향 90도 회전

            int nx = x + dx[d];
            int ny = y + dy[d];

            // 이동한 좌표가 유효 좌표인 경우
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2;
                    count++;
                    clean(nx, ny, d);
                    return; // 재귀 호출이 끝나면 종료
                }
            }
        }
        // 청소 가능한 범위는 모두 마쳤음. 이제는 더 이상 청소할 빈칸이 없음

        // 2번 조건: 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        int tmpDir = (d + 2) % 4; // 후진할 방향 (바라보는 방향은 d 그대로 유지)
        int nx = x + dx[tmpDir];
        int ny = y + dy[tmpDir];

        if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
            if (map[nx][ny] != 1) {
                clean(nx, ny, d);
            }
        }
    }

}
