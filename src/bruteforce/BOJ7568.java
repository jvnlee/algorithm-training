package bruteforce;

import java.util.*;

public class BOJ7568 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] size = new int[n][2];

        for (int i = 0; i < n; i++) {
            size[i][0] = scanner.nextInt();
            size[i][1] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int rank = 1;

            for (int j = 0; j < n; j++) {
                if (i == j) continue; // 자신은 비교 대상에서 제외
                if (size[i][0] < size[j][0] && size[i][1] < size[j][1]) rank++; // 자신보다 큰 사람이 있으면 등수 한칸 아래로
            }

            sb.append(rank).append(" ");
        }

        System.out.println(sb);
    }
}
