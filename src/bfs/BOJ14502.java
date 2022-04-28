package bfs;

import java.util.*;

public class BOJ14502 {

    public static int n;
    public static int m;
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int answer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        answer = 0;

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        dfs(0); // 벽 세우기 시작 (벽이 0개일 때부터 시작)

        System.out.println(answer);
    }

    public static void dfs(int wallNum) {
        if (wallNum == 3) {
            bfs(); // 벽이 3개 모두 세워지면 바이러스 퍼뜨리기 시작
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽 1개를 세움
                    dfs(wallNum + 1); // 벽의 개수가 이전보다 1개 늘어남
                    map[i][j] = 0; // 빈칸으로 원상복구
                }
            }
        }
    }

    public static void bfs() {
        int[][] virusMap = new int[n][m]; // 바이러스가 확산된 모습을 나타낼 지도
        Queue<Virus> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                virusMap[i][j] = map[i][j]; // 우선 기존 지도를 그대로 복사
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virusMap[i][j] == 2) queue.offer(new Virus(i, j));
                // 지도상의 현 좌표가 바이러스라면, 큐에 넣음 (확산을 위해)
            }
        }

        while (!queue.isEmpty()) {
            Virus v = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (virusMap[nx][ny] == 0) {
                        // 바이러스 칸의 상하좌우 중 빈칸이 있다면 확산
                        virusMap[nx][ny] = 2;
                        queue.offer(new Virus(nx, ny)); // 새로 만들어진 바이러스 칸도 큐에 넣음
                    }
                }
            }
        }

        countSafeArea(virusMap); // 확산이 모두 끝난 뒤, 안전 지역 개수 세기
    }

    public static void countSafeArea(int[][] virusMap) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virusMap[i][j] == 0) count++;
            }
        }

        answer = Math.max(answer, count); // 지금까지의 안전지역 개수 중 최대값과 현재 케이스에서 센 안전지역의 개수 중에서 더 큰 값 적용
    }

    public static class Virus {
        int x;
        int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
