package programmers.high_score_kit.greedy;

import java.util.*;

public class PRG42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (r1, r2) -> r1[1] - r2[1]);

        int answer = 0;
        int lastCamera = -30001;

        for (int[] route : routes) {
            if (route[0] > lastCamera) {
                answer++;
                lastCamera = route[1];
            }
        }

        return answer;
    }
}
