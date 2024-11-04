package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ11779 {
    private static List<List<Road>> roads;
    private static int[] distances, prevCities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        roads = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            roads.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            roads.get(from).add(new Road(to, weight));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        prevCities = new int[n + 1];
        Arrays.fill(prevCities, -1);

        dijkstra(start);

        StringBuilder answer = new StringBuilder();
        answer.append(distances[end]).append("\n");

        Stack<Integer> cities = new Stack<>();
        int cur = end;

        while (cur != -1) {
            cities.push(cur);
            cur = prevCities[cur];
        }

        answer.append(cities.size()).append("\n");

        while (!cities.isEmpty()) {
            answer.append(cities.pop()).append(" ");
        }

        System.out.println(answer);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            int curTo = cur.to;
            int curWeight = cur.weight;

            if (distances[curTo] < curWeight) {
                continue;
            }

            for (Road adj : roads.get(curTo)) {
                int adjTo = adj.to;
                int adjWeight = adj.weight;

                if (distances[curTo] + adjWeight < distances[adjTo]) {
                    distances[adjTo] = distances[curTo] + adjWeight;
                    prevCities[adjTo] = curTo;
                    pq.offer(new Road(adjTo, distances[adjTo]));
                }
            }
        }
    }

    private static class Road implements Comparable<Road> {
        int to;
        int weight;

        public Road(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Road r) {
            return weight - r.weight;
        }
    }
}
