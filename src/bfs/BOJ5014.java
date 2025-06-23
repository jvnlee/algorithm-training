package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F + 1];
        Queue<int[]> q = new LinkedList<>();

        visited[S] = true;
        q.offer(new int[]{S, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int level = cur[0];
            int moveCount = cur[1];

            if (level == G) {
                System.out.println(moveCount);
                return;
            }

            if (level + U <= F && !visited[level + U]) {
                q.offer(new int[]{level + U, moveCount + 1});
                visited[level + U] = true;
            }

            if (level - D >= 1 && !visited[level - D]) {
                q.offer(new int[]{level - D, moveCount + 1});
                visited[level - D] = true;
            }
        }

        System.out.println("use the stairs");
    }
}
