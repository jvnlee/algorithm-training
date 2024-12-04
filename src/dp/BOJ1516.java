package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] indegree = new int[N + 1];

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int node = 1; node <= N; node++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            time[node] = t;

            int pre;

            while ((pre = Integer.parseInt(st.nextToken())) != -1) {
                graph.get(pre).add(node);
                indegree[node]++;
            }
        }

        // 위상 정렬 활용
        Queue<Integer> queue = new LinkedList<>();
        int[] totalPreTime = new int[N + 1]; // totalPreTime[i]: i번째 건물을 짓는데 필요한 선행 건물의 총 건설 시간

        for (int node = 1; node <= N; node++) {
            if (indegree[node] == 0) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                indegree[next]--;

                // time[cur]의 최대값을 기반으로 갱신 (선행 건물 중 가장 오래걸리는 것이 나머지 건설 시간을 커버하므로)
                totalPreTime[next] = Math.max(totalPreTime[next], totalPreTime[cur] + time[cur]);

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int node = 1; node <= N; node++) {
            answer.append(totalPreTime[node] + time[node]).append("\n");
        }

        System.out.println(answer);
    }
}
