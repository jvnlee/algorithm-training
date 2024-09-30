package programmers;

import java.util.*;

public class PRG67259 {
    public int solution(int[][] board) {
        int answer = 1_000_000;
        int rows = board.length;
        int cols = board[0].length;

        int[][][] cost = new int[rows][cols][4];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Arrays.fill(cost[i][j], 1_000_000);
            }
        }

        // 시작 좌표에 대한 비용 0으로 초기화
        cost[0][0][1] = 0; // 동쪽으로 진행
        cost[0][0][2] = 0; // 남쪽으로 진행

        int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
        int[] dy = {0, 1, 0, -1};

        Queue<Position> q = new LinkedList<>();

        q.offer(new Position(0, 0, 1, 0)); // 동쪽으로 진행
        q.offer(new Position(0, 0, 2, 0)); // 남쪽으로 진행

        while (!q.isEmpty()) {
            Position cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int total = cur.total;

            if (x == rows - 1 && y == cols - 1) {
                answer = Math.min(answer, total);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) {
                    continue; // 범위 밖을 벗어난 경우 진행 불가
                }

                if (board[nx][ny] == 1) {
                    continue; // 벽인 경우 진행 불가
                }

                int newTotal = (i == dir) ? (total + 100) : (total + 600);

                if (newTotal < cost[nx][ny][i]) {
                    cost[nx][ny][i] = newTotal;
                    q.offer(new Position(nx, ny, i, newTotal));
                }
            }
        }

        return answer;
    }

    private class Position {
        int x;
        int y;
        int dir;
        int total;

        public Position(int x, int y, int dir, int total) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.total = total;
        }
    }
}
