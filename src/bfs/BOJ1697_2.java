package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] road = new int[100_001];

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        road[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == k) {
                System.out.println(road[cur] - 1);
                break;
            }

            if (cur - 1 >= 0 && road[cur - 1] == 0) {
                road[cur - 1] = road[cur] + 1;
                q.offer(cur - 1);
            }

            if (cur + 1 <= 100_000 && road[cur + 1] == 0) {
                road[cur + 1] = road[cur] + 1;
                q.offer(cur + 1);
            }

            if (cur * 2 <= 100_000 && road[cur * 2] == 0) {
                road[cur * 2] = road[cur] + 1;
                q.offer(cur * 2);
            }
        }
    }
}
