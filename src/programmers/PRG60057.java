package programmers;

import java.util.*;

public class PRG60057 {
    public int solution(String s) {
        int answer = 1000;
        int max = s.length();

        if (max == 1) return 1;

        for (int i = 1; i < max; i++) {
            Map<String, Integer> map = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < max; j += i) {
                String substr = s.substring(j, Math.min(j + i, max));

                if (map.containsKey(substr)) {
                    map.put(substr, map.get(substr) + 1);
                } else {
                    compress(map, sb);
                    map = new HashMap<>();
                    map.put(substr, 1);
                }
            }

            compress(map, sb);
            answer = Math.min(answer, sb.length());
        }

        return answer;
    }

    private static void compress(Map<String, Integer> map, StringBuilder sb) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();

            if (v == 1) sb.append(k);
            else sb.append(v).append(k);
        }
    }
}
