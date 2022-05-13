package greedy;

import java.util.*;

public class BOJ1715 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(scanner.nextLong());
        }

        long count = 0;

        while (pq.size() >= 2) {
            long d1 = pq.poll();
            long d2 = pq.poll();

            count += d1 + d2;

            pq.add(d1 + d2);
        }

        System.out.println(count);
    }
}
