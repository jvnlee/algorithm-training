package dfs;

import java.util.*;

public class BOJ2667 {

    public static int n;
    public static int[][] map;
    public static int num = 0;

    public static int dfs(int x, int y) {

        if (x < 0 || x > (n - 1) || y < 0 || y > (n - 1)) return 0;

        if (map[x][y] == 1) {
            map[x][y] = 0;
            num++;

            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);

            return num;
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt(); // 정방형 지도의 가로 세로 길이
        scanner.nextLine();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int count = 0; // 단지 개수
        ArrayList<Integer> aptNums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int aptNum = dfs(i, j);
                if (aptNum != 0) {
                    count++;
                    aptNums.add(aptNum);
                }
                num = 0;
            }
        }

        System.out.println(count);
        aptNums.sort(null);
        aptNums.forEach(System.out::println);
    }
}

