package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ24445 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (List<Integer> edges : graph) {
            edges.sort(Collections.reverseOrder());
        }

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[n + 1];
        int[] order = new int[n + 1];
        int count = 1;

        queue.offer(r);
        visited[r] = true;
        order[r] = count++;

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int x : graph.get(poll)) {
                if (!visited[x]) {
                    queue.offer(x);
                    visited[x] = true;
                    order[x] = count++;
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i < order.length; i++) {
            answer.append(order[i]).append("\n");
        }

        System.out.println(answer);
    }
}
