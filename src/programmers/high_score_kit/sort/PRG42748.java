package programmers.high_score_kit.sort;

import java.util.*;

public class PRG42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < answer.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int cmdIdx = commands[i][2] - 1;

            int[] sub = Arrays.copyOfRange(array, start, end);

            Arrays.sort(sub);

            answer[i] = sub[cmdIdx];
        }

        return answer;
    }
}
