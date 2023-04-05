package programmers;

public class PRG1829 {
    public static int[][] pic;
    public static boolean[][] visited;
    public static int size = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int[] solution(int m, int n, int[][] picture) {
        pic = picture;
        visited = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pic[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    dfs(i, j);
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                size = 0;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        size++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= pic.length || ny < 0 || ny >= pic[0].length) continue;

            if (pic[x][y] == pic[nx][ny] && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}
