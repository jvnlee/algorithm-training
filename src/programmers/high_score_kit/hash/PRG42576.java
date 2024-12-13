package programmers.high_score_kit.hash;

import java.util.*;

public class PRG42576 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> completionMap = new HashMap<>();

        for (String c : completion) {
            completionMap.put(c, completionMap.getOrDefault(c, 0) + 1);
        }

        for (String p : participant) {
            if (completionMap.containsKey(p) && completionMap.get(p) > 0) {
                completionMap.put(p, completionMap.get(p) - 1);
            } else {
                return p;
            }
        }

        return "";
    }
}
