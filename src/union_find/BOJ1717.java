package union_find;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1717 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(a, b);
            } else if (op == 1) {
                if (find(a) == find(b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        int ra = find(a); // 노드 a의 루트
        int rb = find(b); // 노드 b의 루트

        if (ra == rb) { // 루트가 같다면 이미 연결되어 있으므로 종료
            return;
        }

        if (ra < rb) { // 더 작은 값이 루트가 되도록 연결
            parents[rb] = ra;
        } else {
            parents[ra] = rb;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node; // 자기 자신을 값으로 가지고 있다면 해당 노드가 루트
        } else {
            return parents[node] = find(parents[node]); // 부모 노드가 최종적으로 루트가 될 수 있도록 재귀하며 갱신
        }
    }
}
