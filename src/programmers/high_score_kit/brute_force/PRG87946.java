package programmers.high_score_kit.brute_force;

public class PRG87946 {
    private static boolean[] visited;
    private static int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        backtrack(dungeons, k, 0);
        return answer;
    }

    private void backtrack(int[][] dungeons, int currentStamina, int count) {
        answer = Math.max(answer, count);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && currentStamina >= dungeons[i][0]) {
                visited[i] = true;
                backtrack(dungeons, currentStamina - dungeons[i][1], count + 1);
                visited[i] = false;
            }
        }
    }
}