package programmers.high_score_kit.binary_search.retries;

import java.util.*;

public class PRG43238_2 {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times);

        long lo = times[0];
        long hi = (long) n * times[times.length - 1];

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            long count = 0;

            for (int t : times) {
                count += mid / t;
            }

            if (count >= n) {
                answer = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return answer;
    }
}
