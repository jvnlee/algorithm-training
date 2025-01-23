package programmers.high_score_kit.dfs_bfs;

import java.util.*;

public class PRG43164 {
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private List<String> travelHistory = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        for (String[] t : tickets) {
            graph.putIfAbsent(t[0], new PriorityQueue<>());
            graph.get(t[0]).offer(t[1]);
        }

        dfs("ICN");

        Collections.reverse(travelHistory);

        String[] answer = new String[travelHistory.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = travelHistory.get(i);
        }

        return answer;
    }

    private void dfs(String curCity) {
        while (graph.containsKey(curCity) && !graph.get(curCity).isEmpty()) {
            dfs(graph.get(curCity).poll());
        }

        travelHistory.add(curCity);
    }
}