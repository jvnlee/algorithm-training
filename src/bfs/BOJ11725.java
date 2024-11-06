package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] parents = new int[N + 1];

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : graph.get(cur)) {
                if (visited[adj]) {
                    continue;
                }

                q.offer(adj);
                visited[adj] = true;
                parents[adj] = cur;
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 2; i <= N; i++) {
            answer.append(parents[i]).append("\n");
        }

        System.out.println(answer);
    }
}
