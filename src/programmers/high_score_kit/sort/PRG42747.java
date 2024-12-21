package programmers.high_score_kit.sort;

import java.util.*;

public class PRG42747 {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int len = citations.length;
        int answer = 0;

        for (int i = 0; i < len; i++) {
            int count = len - i;

            answer = Math.max(answer, Math.min(count, citations[i]));
        }

        return answer;
    }
}
