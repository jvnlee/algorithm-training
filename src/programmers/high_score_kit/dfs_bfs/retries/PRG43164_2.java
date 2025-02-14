package programmers.high_score_kit.dfs_bfs.retries;

import java.util.*;

public class PRG43164_2 {
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private LinkedList<String> travelHistory = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        for (String[] t : tickets) {
            graph.putIfAbsent(t[0], new PriorityQueue<>());
            graph.get(t[0]).offer(t[1]);
        }

        dfs("ICN");

        return travelHistory.toArray(new String[0]);
    }

    private void dfs(String curCity) {
        while (graph.containsKey(curCity) && !graph.get(curCity).isEmpty()) {
            dfs(graph.get(curCity).poll());
        }

        travelHistory.addFirst(curCity);
    }
}