package greedy;

import java.util.*;

public class BOJ11000 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Lecture[] lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            lectures[i] = new Lecture(scanner.nextInt(), scanner.nextInt());
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for (int i = 1; i < n; i++) {
            if (pq.peek() <= lectures[i].start) pq.poll();
            pq.offer(lectures[i].end);
        }

        System.out.println(pq.size());
    }

    public static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
