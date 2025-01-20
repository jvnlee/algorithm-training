package programmers.high_score_kit.dfs_bfs;

public class PRG43162 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n, computers, visited);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int current, int n, int[][] computers, boolean[] visited) {
        visited[current] = true;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[current][i] == 1) {
                dfs(i, n, computers, visited);
            }
        }
    }
}
