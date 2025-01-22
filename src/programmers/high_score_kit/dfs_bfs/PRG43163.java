package programmers.high_score_kit.dfs_bfs;

import java.util.*;

public class PRG43163 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        List<String> wordList = Arrays.asList(words);

        if (!wordList.contains(target)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            String curWord = cur.word;
            int curCount = cur.count;

            if (curWord.equals(target)) {
                return curCount;
            }

            for (String word : words) {
                if (!visited.contains(word) && canTransform(curWord, word)) {
                    visited.add(word);
                    queue.offer(new Node(word, curCount + 1));
                }
            }
        }

        return 0;
    }

    private boolean canTransform(String begin, String target) {
        int diff = 0;

        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != target.charAt(i)) {
                diff++;
            }

            if (diff > 1) {
                return false;
            }
        }

        return diff == 1;
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
