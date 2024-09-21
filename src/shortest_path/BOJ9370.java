package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9370 {
    private static List<List<Road>> graph;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            int[] candidates = new int[t];

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if ((a == g && b == h) || (a == h && b == g)) {
                    graph.get(a).add(new Road(b, 2 * d - 1));
                    graph.get(b).add(new Road(a, 2 * d - 1));
                } else {
                    graph.get(a).add(new Road(b, 2 * d));
                    graph.get(b).add(new Road(a, 2 * d));
                }
            }

            for (int j = 0; j < t; j++) {
                candidates[j] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(candidates);

            distance = new int[n + 1];
            Arrays.fill(distance, 50_000_000);
            distance[s] = 0;

            dijkstra(s);

            for (int j = 0; j < t; j++) {
                if (distance[candidates[j]] % 2 != 0) {
                    answer.append(candidates[j]).append(" ");
                }
            }

            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road poll = pq.poll();
            int curTo = poll.to;
            int curDist = poll.distance;

            if (distance[curTo] < curDist) {
                continue;
            }

            for (Road adj : graph.get(curTo)) {
                int adjTo = adj.to;
                int adjDist = adj.distance;

                if (distance[curTo] + adjDist < distance[adjTo]) {
                    distance[adjTo] = distance[curTo] + adjDist;
                    pq.offer(new Road(adjTo, distance[adjTo]));
                }
            }
        }
    }

    private static class Road implements Comparable<Road> {
        int to;
        int distance;

        public Road(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return distance - o.distance;
        }
    }
}
