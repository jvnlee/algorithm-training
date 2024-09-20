package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사다리
        int m = Integer.parseInt(st.nextToken()); // 뱀

        int[] ladders = new int[101];
        int[] snakes = new int[101];

        Arrays.fill(ladders, -1);
        Arrays.fill(snakes, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladders[x] = y;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snakes[u] = v;
        }

        int[] counts = new int[101];
        boolean[] visited = new boolean[101];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int curPos = q.poll();

            if (curPos == 100) {
                System.out.println(counts[curPos]);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPos = curPos + i;

                if (nextPos >= 1 && nextPos <= 100) {
                    if (ladders[nextPos] != -1) {
                        nextPos = ladders[nextPos];
                    } else if (snakes[nextPos] != -1) {
                        nextPos = snakes[nextPos];
                    }

                    if (!visited[nextPos]) {
                        counts[nextPos] = counts[curPos] + 1;
                        visited[nextPos] = true;
                        q.offer(nextPos);
                    }
                }
            }
        }
    }
}
