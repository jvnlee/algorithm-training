package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ2667_3 {
    private static int[][] map;
    private static boolean[][] visited;
    private static int n, count;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j)) {
                    nums.add(count);
                    count = 0;
                }
            }
        }

        Collections.sort(nums);

        StringBuilder answer = new StringBuilder();
        answer.append(nums.size()).append("\n");

        for (int num : nums) {
            answer.append(num).append("\n");
        }

        System.out.println(answer);
    }

    private static boolean dfs(int x, int y) {
        if (map[x][y] == 0 || visited[x][y]) {
            return false;
        }

        visited[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }

        return true;
    }
}
