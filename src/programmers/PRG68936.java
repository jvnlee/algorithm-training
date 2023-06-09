package programmers;

import java.util.*;

public class PRG68936 {
    public int[] dx = {-1, 1, 0, 0};
    public int[] dy = {0, 0, -1, 1};

    public int[] solution(int[][] arr) {
        int[] answer = {};

        int n = arr.length;

        dfs(arr, 0, 0, n, n);

        int zero = 0;
        int one = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (arr[i][j] == 0) {
                    zero++;
                } else if (arr[i][j] == 1) {
                    one++;
                }
            }
        }

        answer = new int[]{zero, one};

        return answer;
    }

    public void dfs(int[][] arr, int x1, int y1, int x2, int y2){
        if (x2 - x1 == 1 && y2 - y1 == 1) return;

        boolean isAllSameNumber = isSame(arr, x1, y1, x2, y2);

        if (isAllSameNumber) {
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    if (i == x1 && j == y1) continue;
                    arr[i][j] = -1;
                }
            }
        } else {
            int len = (x2 - x1) / 2;
            dfs(arr, x1, y1, x1 + len, y1 + len);
            dfs(arr, x1, y1 + len, x1 + len, y2);
            dfs(arr, x1 + len, y1, x2, y1 + len);
            dfs(arr, x1 + len, y1 + len, x2, y2);
        }
    }

    public boolean isSame(int[][] arr, int x1, int y1, int x2, int y2){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x1, y1});
        boolean[][] visited = new boolean[arr.length][arr.length];
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            int[] cn = queue.poll();
            int cx = cn[0];
            int cy = cn[1];

            if (arr[x1][y1] != arr[cx][cy]) return false;

            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (!(nx >= x1 && nx < x2 && ny >= y1 && ny < y2)) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }

        return true;
    }
}
