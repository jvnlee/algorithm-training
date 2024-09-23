package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1956 {
    private static int V;
    private static int INF = 1_000_000_000;
    private static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        distance = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a][b] = c;
        }

        floydWarshall();

        int answer = INF;

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j && distance[i][j] != INF && distance[j][i] != INF) {
                    answer = Math.min(answer, distance[i][j] + distance[j][i]);
                }
            }
        }

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void floydWarshall() {
        for (int mid = 1; mid <= V; mid++) {
            for (int from = 1; from <= V; from++) {
                for (int to = 1; to <= V; to++) {
                    distance[from][to] = Math.min(distance[from][to], distance[from][mid] + distance[mid][to]);
                }
            }
        }
    }
}
