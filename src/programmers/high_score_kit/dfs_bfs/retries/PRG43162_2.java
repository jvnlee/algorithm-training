package programmers.high_score_kit.dfs_bfs.retries;

public class PRG43162_2 {
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

    private void dfs(int c, int n, int[][] computers, boolean[] visited) {
        visited[c] = true;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[c][i] == 1) {
                dfs(i, n, computers, visited);
            }
        }
    }
}