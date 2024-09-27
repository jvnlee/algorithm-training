package programmers;

import java.util.*;
import java.util.regex.*;

public class PRG64064 {
    Set<Set<String>> combinations;
    List<List<String>> candidates;

    public int solution(String[] user_id, String[] banned_id) {
        combinations = new HashSet<>();
        candidates = new ArrayList<>();

        for (String bid : banned_id) {
            List<String> candidate = new ArrayList<>();

            for (String uid : user_id) {
                if (uid.length() != bid.length()) {
                    continue;
                }

                if (Pattern.matches(bid.replace('*', '.'), uid)) {
                    candidate.add(uid);
                }
            }

            candidates.add(candidate);
        }

        dfs(new HashSet<>(), 0);

        return combinations.size();
    }

    private void dfs(Set<String> combination, int depth) {
        if (depth == candidates.size()) {
            combinations.add(new HashSet<>(combination));
            return;
        }

        for (String uid : candidates.get(depth)) {
            if (combination.add(uid)) {
                dfs(combination, depth + 1);
                combination.remove(uid);
            }
        }
    }
}
