package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;

public class BOJ2667_2 {
    public static int[][] map;
    public static boolean[][] visited;
    public static int n, houseCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = getNumericValue(s.charAt(j));
            }
        }

        // 단지별 집 개수를 저장
        List<Integer> houseCounts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 특정 칸을 시작으로 가능한 모든 주변 칸을 탐색해서 단지에 들어가는 집의 수를 구해옴
                if (dfs(i, j)) {
                    houseCounts.add(houseCount);
                    houseCount = 0; // 단지에 들어가는 집의 숫자 초기화
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 리스트의 크기가 곧 단지의 수
        sb.append(houseCounts.size()).append("\n");

        // 오름차순 정렬
        sort(houseCounts);

        houseCounts.forEach(num -> sb.append(num).append("\n"));
        System.out.println(sb);
    }

    public static boolean dfs(int x, int y) {
        // 범위를 벗어나는 칸인 경우 탐색 즉시 종료
        if (x <= -1 || x >= n || y <= -1 || y >= n) {
            return false;
        }

        // 아직 방문하지 않았고, 집이 있는 칸이라면
        if (!visited[x][y] && map[x][y] == 1) {
            visited[x][y] = true; // 방문 처리

            houseCount++; // 해당 단지에 들어가는 아파트 개수 +1

            // 상하좌우 탐색
            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);

            return true;
        }

        return false;
    }
}
