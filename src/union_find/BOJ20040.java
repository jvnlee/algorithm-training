package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20040 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 사이클 체크
            // 유니온 하기 전에 이미 루트가 같다면 유니온 시 사이클이 됨
            if (find(a) == find(b)) {
                System.out.println(i + 1);
                return;
            }

            union(a, b);
        }

        // m번의 차례가 모두 진행된 후에도 사이클 감지가 안된 경우는 0을 출력
        System.out.println(0);
    }

    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return;
        }

        if (ra < rb) {
            parents[rb] = ra;
        } else {
            parents[ra] = rb;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }
}
