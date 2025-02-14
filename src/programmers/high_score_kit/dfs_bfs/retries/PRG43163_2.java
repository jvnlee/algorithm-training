package programmers.high_score_kit.dfs_bfs.retries;

import java.util.*;

public class PRG43163_2 {
    public int solution(String begin, String target, String[] words) {
        Set<String> visited = new HashSet<>();

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(begin, 0));

        while(!q.isEmpty()) {
            Node polled = q.poll();
            String curWord = polled.word;
            int curCount = polled.count;

            if (curWord.equals(target)) {
                return curCount;
            }

            for (String word : words) {
                if (!visited.contains(word) && isChangeable(curWord, word)) {
                    visited.add(word);
                    q.offer(new Node(word, curCount + 1));
                }
            }
        }

        return 0;
    }

    private boolean isChangeable(String w1, String w2) {
        int count = 0;

        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                count++;

                if (count >= 2) {
                    return false;
                }
            }
        }

        return true;
    }

    private class Node {
        String word;
        int count;

        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
