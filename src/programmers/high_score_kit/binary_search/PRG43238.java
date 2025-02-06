package programmers.high_score_kit.binary_search;

import java.util.*;

public class PRG43238 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = 0;
        long lo = 1;
        long hi = (long) times[times.length - 1] * n;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            long count = 0;

            for (int t : times) {
                count += mid / t;

                if (count >= n) {
                    break;
                }
            }

            if (count < n) {
                lo = mid + 1;
            } else {
                answer = mid;
                hi = mid - 1;
            }
        }

        return answer;
    }
}