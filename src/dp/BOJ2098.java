package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098 {
    private static int N;
    private static int INF = 16_000_000;
    private static int[][] dist, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dist = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N]; // 2^N 비트 마스크 사용
        // dp[i][j]: 현재 i번 도시에 있을 때, i번 도시에서 출발하여 방문하지 않은 다른 도시들을 모두 방문하고 최초 도시로 돌아오는 최소 비용

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(tsp(0, 1)); // 0번 도시에서 시작 (어디서 시작하든 똑같다)
    }

    // TSP: Traveling Salesman Problem
    private static int tsp(int city, int mask) {
        if (mask == (1 << N) - 1) { // 111...111 (1이 N개) = 모든 도시 방문 완료
            return dist[city][0] == 0 ? INF : dist[city][0]; // 현재 도시 -> 시작점(0번 도시) 경로가 존재 여부에 따라 값 반환
        }

        if (dp[city][mask] != -1) { // -1이 아닌 경우는 이미 해당 도시를 방문해서 거리 계산이 완료된 경우이므로
            return dp[city][mask]; // 메모이제이션 값을 사용
        }

        dp[city][mask] = INF; // 현재 도시 방문 표시

        for (int nextCity = 0; nextCity < N; nextCity++) {
            if (dist[city][nextCity] == 0 || (mask & (1 << nextCity)) != 0) {
                continue; // 현재 도시 -> 다음 도시 경로가 존재하지 않거나, 다음 도시를 이미 방문한 경우는 패스
            }

            int nextMask = mask | (1 << nextCity); // 다음 도시 방문 처리

            dp[city][mask] = Math.min(dp[city][mask], tsp(nextCity, nextMask) + dist[city][nextCity]);
        }

        return dp[city][mask];
    }
}
