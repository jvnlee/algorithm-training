package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int d = parseInt(st.nextToken());

        int[] dp = new int[d + 1]; // dp[i]: 위치 0부터 위치 i까지의 최단거리

        for (int i = 0; i < dp.length; i++) {
            dp[i] = i; // 위치 0부터 위치 i까지 가는 거리 = i (지름길 없이 정직하게 간 경우)
        }

        int[][] shortcuts = new int[n][3]; // 지름길 목록

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            int length = parseInt(st.nextToken());

            if (length < (end - start)) { // 지름길의 길이가 실제 거리보다 짧은 경우에만 목록에 추가
                shortcuts[i][0] = start;
                shortcuts[i][1] = end;
                shortcuts[i][2] = length;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int[] shortcut : shortcuts) {
                int start = shortcut[0];
                int end = shortcut[1];
                int length = shortcut[2];

                if (i == end) { // 현재 위치로 도착 가능한 지름길이 있다면, dp 배열의 최단거리 값 갱신
                    dp[i] = Math.min(
                            dp[i], // 출발 도착 지점이 동일한데 길이가 더 긴 지름길이 있으면 긴 지름길을 탄 기준으로 갱신되어버리므로 기존값도 함께 고려
                            Math.min(
                                    dp[i - 1] + 1, // 직전 위치에서 단순히 1km 전진한 경우
                                    dp[start] + length // 해당 지름길을 탄 경우 (지름길 시작 위치까지의 최단거리 + 지름길 길이)
                            )
                    );
                } else {
                    // 직전 위치에 지름길을 타고 왔을 수 있으므로 직전 위치에서 1km 전진한 값과 기존 값 중 최소값 선택
                    dp[i] = Math.min(dp[i], dp[i - 1] + 1);
                }
            }
        }

        System.out.println(dp[d]);
    }
}
