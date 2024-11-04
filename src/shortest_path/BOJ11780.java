package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ11780 {
    private static int n;
    private static int[][] cost, prev;
    private static final int INF = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        cost = new int[n + 1][n + 1];

        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (start == end) {
                    cost[start][end] = 0;
                } else {
                    cost[start][end] = INF;
                }
            }
        }

        StringTokenizer st;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cost[a][b] = Math.min(cost[a][b], c);
        }

        prev = new int[n + 1][n + 1];

        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (cost[start][end] != INF && start != end) {
                    prev[start][end] = start;
                }
            }
        }

        floydWarshall();

        StringBuilder answer = new StringBuilder();

        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (cost[start][end] == INF) {
                    answer.append("0").append(" ");
                } else {
                    answer.append(cost[start][end]).append(" ");
                }
            }

            answer.append("\n");
        }

        Stack<Integer> stack;
        StringBuilder path;

        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (start != end && cost[start][end] != INF) {
                    stack = new Stack<>();
                    path = new StringBuilder();

                    int cur = end;

                    while (cur != start) {
                        if (cur == 0) {
                            path.append("0");
                        }

                        stack.push(cur);
                        cur = prev[start][cur];
                    }

                    stack.push(start);

                    answer.append(stack.size()).append(" ");

                    while (!stack.isEmpty()) {
                        path.append(stack.pop()).append(" ");
                    }

                    answer.append(path).append("\n");
                } else {
                    answer.append("0").append("\n");
                }
            }
        }

        System.out.println(answer);
    }

    private static void floydWarshall() {
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (cost[start][mid] + cost[mid][end] < cost[start][end]) {
                        cost[start][end] = cost[start][mid] + cost[mid][end];
                        prev[start][end] = prev[mid][end];
                    }
                }
            }
        }
    }
}
