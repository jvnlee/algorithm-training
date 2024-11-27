package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] time = new int[N + 1];

            for (int j = 1; j <= N; j++) {
                time[j] = Integer.parseInt(st.nextToken());
            }

            List<List<Integer>> conditions = new ArrayList<>();

            for (int j = 0; j <= N; j++) {
                conditions.add(new ArrayList<>());
            }

            int[] inDegrees = new int[N + 1];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                conditions.get(x).add(y);
                inDegrees[y]++;
            }

            int[] dp = new int[N + 1];
            Queue<Integer> q = new LinkedList<>();

            for (int j = 1; j <= N; j++) {
                dp[j] = time[j];

                if (inDegrees[j] == 0) {
                    q.offer(j);
                }
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : conditions.get(cur)) {
                    dp[next] = Math.max(dp[next], dp[cur] + time[next]);

                    inDegrees[next]--;

                    if (inDegrees[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            int W = Integer.parseInt(br.readLine());

            answer.append(dp[W]).append("\n");
        }

        System.out.println(answer);
    }
}
