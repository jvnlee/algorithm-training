package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int isConnected = Integer.parseInt(st.nextToken());

                if (isConnected == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int rootCity = find(Integer.parseInt(st.nextToken()));

        for (int i = 1; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());

            if (rootCity != find(city)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry) {
            return;
        }

        if (rx > ry) {
            parents[rx] = ry;
        } else {
            parents[ry] = rx;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }
}
