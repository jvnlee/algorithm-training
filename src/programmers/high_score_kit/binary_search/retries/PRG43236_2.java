package programmers.high_score_kit.binary_search.retries;

import java.util.*;

public class PRG43236_2 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int lo = 1;
        int hi = distance;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int removedRocks = 0;
            int curPos = 0;

            for (int r : rocks) {
                if (r - curPos < mid) {
                    removedRocks++;
                } else {
                    curPos = r;
                }
            }

            if (distance - curPos < mid) {
                removedRocks++;
            }

            if (removedRocks <= n) {
                answer = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return answer;
    }
}
