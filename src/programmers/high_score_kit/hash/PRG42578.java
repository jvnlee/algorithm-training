package programmers.high_score_kit.hash;

import java.util.*;

public class PRG42578 {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> clothesMap = new HashMap<>();

        for (String[] c : clothes) {
            clothesMap.put(c[1], clothesMap.getOrDefault(c[1], 0) + 1);
        }

        for (String category : clothesMap.keySet()) {
            answer *= clothesMap.get(category) + 1;
        }

        return answer - 1;
    }
}
