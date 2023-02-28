package 내배코.w3;

import java.util.*;

public class W3_4_1 {
    public int solution(int[][] triangle) {
        // DP (삼각형을 이루는 원소 숫자를 위에서부터의 누적합으로 덮어쓰면서 내려감)
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) { // 맨 왼쪽 원소라면
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == i) { // 맨 오른쪽 원소라면
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else { // 그 외에 가운데 원소들이라면
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }

        // 마지막 줄까지 누적합을 구해서 저장해놨으므로 sort 후
        Arrays.sort(triangle[triangle.length - 1]);

        // 가장 끝 값 도출
        return triangle[triangle.length - 1][triangle.length - 1];
    }
}
