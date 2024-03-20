package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class BOJ11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (abs(o1) > abs(o2)) {
                return abs(o1) - abs(o2);
            } else if (abs(o1) == abs(o2)) {
                return o1 - o2;
            } else {
                return -1;
            }
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = parseInt(br.readLine());

            if (x != 0) pq.offer(x);
            else {
                if (pq.isEmpty()) sb.append("0").append("\n");
                else sb.append(pq.poll()).append("\n");
            }
        }

        System.out.println(sb);
    }
}
