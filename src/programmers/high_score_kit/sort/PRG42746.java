package programmers.high_score_kit.sort;

import java.util.*;

public class PRG42746 {
    public String solution(int[] numbers) {
        String[] strNumbers = new String[numbers.length];

        for (int i = 0; i < strNumbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNumbers, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        if (strNumbers[0].equals("0")) {
            return "0";
        }

        StringBuilder answer = new StringBuilder();

        for (String s : strNumbers) {
            answer.append(s);
        }

        return answer.toString();
    }
}
