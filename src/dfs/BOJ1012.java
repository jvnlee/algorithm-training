package dfs;

import java.util.*;

public class BOJ1012 {

    public static int t;
    public static int m;
    public static int n;
    public static int k;
    public static int[][] map;
    public static int wormNum = 0;
    public static StringBuilder answer = new StringBuilder();

    public static boolean dfs(int x, int y) {
        if (x < 0 || x > (m - 1) || y < 0 || y > (n - 1)) return false;

        if (map[x][y] == 0) return false;

        if (map[x][y] == 1) {
            map[x][y] = 0;
            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);

            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            scanner.nextLine();
            m = scanner.nextInt(); // i번째 테스트 케이스의 밭 가로 길이
            n = scanner.nextInt(); // i번째 테스트 케이스의 밭 세로 길이
            k = scanner.nextInt(); // i번째 테스트 케이스의 배추 개수

            map = new int[m][n];

            for (int j = 0; j < k; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                map[x][y] = 1; // 배추 표시
            }

            for (int g = 0; g < m; g++) {
                for (int h = 0; h < n; h++) {
                    if (dfs(g, h)) wormNum++;
                }
            }

            answer.append(wormNum);
            if (i != (t - 1)) answer.append("\n");
            wormNum = 0; // 다음 테스트 케이스를 위해 초기화
        }

        System.out.println(answer);
    }

}
