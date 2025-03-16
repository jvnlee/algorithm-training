package question_collection.samsung.retries;

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

public class BOJ3190_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int ax = Integer.parseInt(st.nextToken()) - 1;
            int ay = Integer.parseInt(st.nextToken()) - 1;

            map[ax][ay] = 1;
        }

        int L = Integer.parseInt(br.readLine());

        Map<Integer, Character> moves = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            moves.put(t, d);
        }

        Deque<int[]> snakeDeq = new LinkedList<>();
        Set<String> snakeSet = new HashSet<>();

        snakeDeq.addFirst(new int[]{0, 0});
        snakeSet.add("0,0");

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int time = 0;
        int dir = 1;

        int hx = 0;
        int hy = 0;

        while (true) {
            time++;
            hx += dx[dir];
            hy += dy[dir];

            if (hx < 0 || hx >= N || hy < 0 || hy >= N) {
                System.out.println(time);
                return;
            }

            if (snakeSet.contains(hx + "," + hy)) {
                System.out.println(time);
                return;
            }

            snakeDeq.addFirst(new int[]{hx, hy});
            snakeSet.add(hx + "," + hy);

            if (map[hx][hy] == 1) {
                map[hx][hy] = 0;
            } else {
                int[] tail = snakeDeq.removeLast();
                snakeSet.remove(tail[0] + "," + tail[1]);
            }

            if (moves.containsKey(time)) {
                if (moves.get(time) == 'L') {
                    dir = (dir + 3) % 4;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
        }
    }
}
