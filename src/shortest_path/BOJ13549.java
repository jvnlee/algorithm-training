package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549 {
    private static int[] time = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N - K); // 수빈이가 더 앞에 있다면 1칸씩 계속 후진하는 것이 최단 경로
        } else {
            Arrays.fill(time, 100_000);
            time[N] = 0;

            dijkstra(N);

            System.out.println(time[K]);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int curPos = poll.pos;
            int curTime = poll.time;

            if (time[curPos] < curTime) {
                continue;
            }

            int move1 = curPos - 1;
            if (move1 >= 0 && curTime + 1 < time[move1]) {
                time[move1] = curTime + 1;
                pq.offer(new Node(move1, time[move1]));
            }

            int move2 = curPos + 1;
            if (move2 <= 100_000 && curTime + 1 < time[move2]) {
                time[move2] = curTime + 1;
                pq.offer(new Node(move2, time[move2]));
            }

            int move3 = curPos * 2;
            if (move3 <= 100_000 && curTime < time[move3]) {
                time[move3] = curTime;
                pq.offer(new Node(move3, time[move3]));
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int pos;
        int time;

        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }
}
