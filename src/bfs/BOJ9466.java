package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] selected = new int[n + 1];
            int[] inDegree = new int[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                selected[i] = Integer.parseInt(st.nextToken());
                inDegree[selected[i]]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int next = selected[cur];

                if (visited[next]) {
                    continue;
                }

                inDegree[next]--;

                if (inDegree[next] == 0) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }

            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i] || selected[i] == i) {
                    count++;
                }
            }

            System.out.println(n - count);
        }
    }
}
