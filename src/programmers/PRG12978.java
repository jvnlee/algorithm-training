package programmers;

public class PRG12978 {
    public boolean[] visited;
    public int[] distance;

    public int solution(int N, int[][] road, int K) {
        visited = new boolean[N];
        distance = new int[N];
        int[][] data = new int[N][N];

        for (int[] r : road) {
            if (data[r[0] - 1][r[1] - 1] != 0 && data[r[0] - 1][r[1] - 1] != 500001) {
                data[r[0] - 1][r[1] - 1] = Math.min(r[2], data[r[0] - 1][r[1] - 1]);
                data[r[1] - 1][r[0] - 1] = data[r[0] - 1][r[1] - 1];
            } else {
                data[r[0] - 1][r[1] - 1] = r[2];
                data[r[1] - 1][r[0] - 1] = r[2];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (data[i][j] == 0) data[i][j] = 500001;
                if (i == j) data[i][j] = 0;
            }
        }

        System.arraycopy(data[0], 0, distance, 0, N);

        visited[0] = true;

        for (int i = 0; i < N - 2; i++) {
            int min = 500001;
            int currentIdx = 0;
            for (int j = 0; j < N; j++) {
                if (distance[j] < min && !visited[j]) {
                    min = distance[j];
                    currentIdx = j;
                }
            }
            visited[currentIdx] = true;
            for (int j = 0; j < N; j++) {
                if (!visited[j]) {
                    if (distance[currentIdx] + data[currentIdx][j] < distance[j]) {
                        distance[j] = distance[currentIdx] + data[currentIdx][j];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (distance[i] <= K) answer++;
        }

        return answer;
    }
}
