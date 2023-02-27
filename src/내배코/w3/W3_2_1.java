package 내배코.w3;

import java.util.*;

public class W3_2_1 {
    public int solution(int[][] maps) {
        int maxX = maps.length - 1; // x 좌표 최대
        int maxY = maps[0].length - 1; // y 좌표 최대

        // x 좌표와 y 좌표 이동 정보: 상, 하, 좌, 우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // BFS
        Queue<Position> queue = new LinkedList<>();

        Position startPos = new Position(0, 0);
        queue.offer(startPos);

        while (!queue.isEmpty()) {
            Position curPos = queue.poll();
            int x = curPos.x;
            int y = curPos.y;

            for (int i = 0; i < 4; i++) {
                // 새 x, y 좌표 생성
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > maxX || ny > maxY) continue; // 새 좌표가 맵 밖으로 벗어난 경우 패스
                if (maps[nx][ny] == 0) continue; // 새 좌표가 벽인 경우 패스

                if (maps[nx][ny] == 1) { // 아직 가보지 않은 유효 좌표
                    maps[nx][ny] = maps[x][y] + 1; // 직전 좌표에 기록된 거리 + 1
                    queue.offer(new Position(nx, ny)); // 새 좌표에 대해 BFS
                }
            }
        }

        int destinationCnt = maps[maxX][maxY];

        // 마지막 좌표에 기록된 값이 1이면 도달하지 못했다는 의미이므로 -1 반환
        return destinationCnt == 1 ? -1 : destinationCnt;
    }

    private class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
    최단 거리 측정 문제는 BFS로 해결 가능
    주어진 지도의 좌표에 지나온 거리(최단 거리)를 기록함
    Queue를 생성해 시작 좌표부터 넣고, Queue에서 하나씩 꺼내와 이동 시켜본 뒤 유효한 이동이면 직전 좌표에 기록된 최단 거리 정보에 1을 더해 저장
    그리고 다시 새 좌표를 Queue에 넣어서 더 이상 이동할 좌표가 없을 때까지 반복
     */
}
