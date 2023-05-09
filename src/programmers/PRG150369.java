package programmers;

import java.util.*;

public class PRG150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> ds = new Stack<>();
        Stack<Integer> ps = new Stack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                ds.push(i + 1);
            }

            for (int j = 0; j < pickups[i]; j++) {
                ps.push(i + 1);
            }
        }

        while (!ds.isEmpty() && !ps.isEmpty()) {
            int lastD = ds.peek();
            int lastP = ps.peek();

            for (int i = 0; i < cap; i++) {
                if (!ds.isEmpty()) ds.pop();
                if (!ps.isEmpty()) ps.pop();
            }

            answer += Math.max(lastD, lastP) * 2L;
        }

        while (!ds.isEmpty()) {
            int lastD = ds.peek();

            for (int i = 0; i < cap; i++) {
                if (!ds.isEmpty()) ds.pop();
            }

            answer += lastD * 2L;
        }

        while (!ps.isEmpty()) {
            int lastP = ps.peek();

            for (int i = 0; i < cap; i++) {
                if (!ps.isEmpty()) ps.pop();
            }

            answer += lastP * 2L;
        }

        return answer;
    }
}
