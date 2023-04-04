package programmers;

import java.util.*;

public class PRG1835 {

    public static String[] cond;
    public static HashMap<Character, Integer> friendsMap;
    public static boolean[] visited;
    public static int[] permutation;
    public static int answer;

    public static int solution(int n, String[] data) {
        cond = data;
        friendsMap = new HashMap<>();
        visited = new boolean[8];
        permutation = new int[8];
        answer = 0;

        friendsMap.put('A', 0);
        friendsMap.put('C', 1);
        friendsMap.put('F', 2);
        friendsMap.put('J', 3);
        friendsMap.put('M', 4);
        friendsMap.put('N', 5);
        friendsMap.put('R', 6);
        friendsMap.put('T', 7);

        dfs(0);

        return answer;
    }

    public static void dfs(int idx) {
        if (idx == 8) { // 완성된 순열 검증
            if (validate()) answer++;
        } else { // 순열 생성
            for (int i = 0; i < 8; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    permutation[idx] = i;
                    dfs(idx + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static boolean validate() {
        for (String c : cond) {
            int friend1 = permutation[friendsMap.get(c.charAt(0))];
            int friend2 = permutation[friendsMap.get(c.charAt(2))];
            char operator = c.charAt(3);
            int condDist = c.charAt(4) - '0' + 1;

            int actualDist = Math.abs(friend1 - friend2);

            if (operator == '=') {
                if (actualDist != condDist) return false;
            } else if (operator == '>') {
                if (actualDist <= condDist) return false;
            } else if (operator == '<') {
                if (actualDist >= condDist) return false;
            }
        }

        return true;
    }

}
