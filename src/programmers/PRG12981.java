package programmers;

import java.util.*;

public class PRG12981 {
    public int[] solution(int n, String[] words) {
        List<String> list = Arrays.asList(words);

        Set<String> set = new HashSet<>();
        set.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            String word = list.get(i);
            String prevWord = list.get(i - 1);

            int idx = list.lastIndexOf(word);

            if (!word.startsWith(prevWord.substring(prevWord.length() - 1)) || set.contains(word)) {
                return new int[]{idx % n + 1, (idx + 1) % n == 0 ? (idx + 1) / n : (idx + 1) / n + 1};
            } else {
                set.add(word);
            }
        }

        return new int[]{0, 0};
    }
}
