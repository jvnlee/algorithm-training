package programmers.high_score_kit.brute_force;

import java.util.*;

public class PRG87946 {
    private static List<String> orders;
    private static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        orders = new ArrayList<>();
        visited = new boolean[dungeons.length];

        StringBuilder indices = new StringBuilder();

        for (int i = 0; i < dungeons.length; i++) {
            indices.append(i);
        }

        permute(indices.toString(), "");

        int answer = 0;

        for (String order : orders) {
            int stamina = k;
            int count = 0;

            for (char c : order.toCharArray()) {
                int index = c - '0';

                if (stamina >= dungeons[index][0]) {
                    stamina -= dungeons[index][1];
                    count++;
                } else {
                    break;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }

    private void permute(String original, String current) {
        if (current.length() == original.length()) {
            orders.add(current);
            return;
        }

        for (int i = 0; i < original.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permute(original, current + original.charAt(i));
                visited[i] = false;
            }
        }
    }
}
