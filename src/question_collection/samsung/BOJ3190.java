package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 보드 크기
        int K = Integer.parseInt(br.readLine()); // 사과 개수

        int[][] map = new int[N][N]; // 맵 정보
        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken()) - 1;
            int ay = Integer.parseInt(st.nextToken()) - 1;
            map[ax][ay] = 1; // 사과 위치
        }

        int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        Map<Integer, Character> moveMap = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            moveMap.put(t, d);
        }

        int time = 0;
        int dir = 1; // 초기 방향: 오른쪽 (0: 북, 1: 동, 2: 남, 3: 서)

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Deque<int[]> snake = new LinkedList<>(); // 뱀의 몸
        Set<String> snakeSet = new HashSet<>(); // 뱀의 위치 저장 (충돌 체크용)

        // 초기 위치
        snake.add(new int[]{0, 0});
        snakeSet.add("0,0");

        // 머리 위치
        int hx = 0;
        int hy = 0;

        while (true) {
            time++;
            hx += dx[dir];
            hy += dy[dir];

            // 벽과 충돌 확인
            if (hx < 0 || hx >= N || hy < 0 || hy >= N) {
                System.out.println(time);
                return;
            }

            // 자기 몸과 충돌 확인
            if (snakeSet.contains(hx + "," + hy)) {
                System.out.println(time);
                return;
            }

            // 머리 이동
            snake.addFirst(new int[]{hx, hy});
            snakeSet.add(hx + "," + hy);

            // 사과가 없는 경우 -> 꼬리 이동
            if (map[hx][hy] == 1) {
                map[hx][hy] = 0; // 사과를 먹음
            } else {
                int[] tail = snake.removeLast();
                snakeSet.remove(tail[0] + "," + tail[1]); // 꼬리 제거
            }

            // 방향 전환 체크
            if (moveMap.containsKey(time)) {
                char d = moveMap.get(time);
                if (d == 'L') {
                    dir = (dir + 3) % 4; // 왼쪽 회전
                } else {
                    dir = (dir + 1) % 4; // 오른쪽 회전
                }
            }
        }
    }
}
