package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404 {
    private static int n;
    private static long INF = 20_000_000_000L;
    private static long[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        distance = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = INF;
                }
            }
        }

        StringTokenizer st;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a][b] = Math.min(distance[a][b], c);
        }

        floydWarshall();

        StringBuilder answer = new StringBuilder();

        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                long dist = distance[from][to];
                if (dist == INF) {
                    answer.append("0").append(" ");
                } else {
                    answer.append(dist).append(" ");
                }
            }

            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void floydWarshall() {
        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    distance[from][to] = Math.min(distance[from][to], distance[from][mid] + distance[mid][to]);
                }
            }
        }
    }
}
