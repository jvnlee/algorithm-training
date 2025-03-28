package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Shark> sharks = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            sharks.add(new Shark(x, y, speed, direction, size));
        }

        int[] dx = {-1, 1, 0, 0}; // 상 하 우 좌
        int[] dy = {0, 0, 1, -1};

        int fisher = 0;
        int answer = 0;

        while (fisher < C + 1) {
            // 낚시왕이 오른쪽으로 한 칸 이동
            fisher++;

            // 낚시왕이 같은 열에 존재하는 상어 중 땅과 가장 가까운 상어 포획
            Shark closestShark = null;

            for (Shark s : sharks) {
                if (s.y == fisher) {
                    if (closestShark == null || s.x < closestShark.x) {
                        closestShark = s;
                    }
                }
            }

            if (closestShark != null) {
                answer += closestShark.size;
                sharks.remove(closestShark);
            }

            // 상어 이동
            Shark[][] sharkMap = new Shark[R + 1][C + 1];

            for (Shark s : sharks) {
                int cycle = (s.direction <= 2) ? 2 * (R - 1) : 2 * (C - 1);
                int move = s.speed % cycle;

                for (int i = 0; i < move; i++) {
                    int nx = s.x + dx[s.direction - 1];
                    int ny = s.y + dy[s.direction - 1];

                    if (nx < 1 || nx > R || ny < 1 || ny > C) {
                        s.direction = (s.direction <= 2) ? 3 - s.direction : 7 - s.direction;

                        nx = s.x + dx[s.direction - 1];
                        ny = s.y + dy[s.direction - 1];
                    }

                    s.x = nx;
                    s.y = ny;
                }

                if (sharkMap[s.x][s.y] == null || sharkMap[s.x][s.y].size < s.size) {
                    sharkMap[s.x][s.y] = s;
                }
            }

            sharks.clear();

            for (int x = 1; x <= R; x++) {
                for (int y = 1; y <= C; y++) {
                    if (sharkMap[x][y] != null) {
                        sharks.add(sharkMap[x][y]);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static class Shark {
        int x;
        int y;
        int speed;
        int direction;
        int size;

        public Shark(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}
