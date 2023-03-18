package 내배코.w4;

import java.util.*;

public class W4_2 {
    public String[] solution(String[] s1, String[] s2, String k) {
        Map<String, List<String>> graph = new HashMap<>();

        for (int i = 0; i < s1.length; i++) {
            if (graph.containsKey(s2[i])) {
                graph.get(s2[i]).add(s1[i]);
            } else {
                graph.put(s2[i], new ArrayList<>());
                graph.get(s2[i]).add(s1[i]);
            }
        }

        Map<String, List<String>> reverseGraph = new HashMap<>();
        Map<String, Integer> requiredCount = new HashMap<>();
        Queue<String> curriculum = new PriorityQueue<>();

        Set<String> visited = new HashSet<>();

        Stack<String> stack = new Stack<>();
        stack.push(k);
        visited.add(k);

        while (!stack.isEmpty()) {
            String targetClass = stack.pop();

            if (!graph.containsKey(targetClass)) {
                curriculum.offer(targetClass);
                continue;
            }

            List<String> requiredClasses = graph.get(targetClass);

            requiredCount.put(targetClass, requiredClasses.size());

            for (String required : requiredClasses) {
                if (reverseGraph.containsKey(required)) {
                    reverseGraph.get(required).add(targetClass);
                } else {
                    reverseGraph.put(required, new ArrayList<>());
                    reverseGraph.get(required).add(targetClass);
                }

                if (visited.contains(required)) continue;
                stack.push(required);
                visited.add(required);
            }
        }

        List<String> answer = new ArrayList<>();

        while (!curriculum.isEmpty()) {
            String currentClass = curriculum.poll();
            answer.add(currentClass);

            for (String nextClass : reverseGraph.getOrDefault(currentClass, new ArrayList<>())) {
                int cnt = requiredCount.get(nextClass) - 1;
                requiredCount.put(nextClass, cnt);

                if (requiredCount.get(nextClass) == 0) {
                    curriculum.offer(nextClass);
                }
            }
        }

        return answer.toArray(String[]::new);
    }
}
