package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
            }

            // 최소 신장 트리의 원칙에 따라 간선 가중치가 없다면 간선이 최소 N - 1개 있을 때 모든 노드를 방문할 수 있음
            // N - 1개 보다 적은 간선으로는 모든 노드를 방문하는 것이 불가능하고, N - 1개보다 많아지면 사이클이 발생함
            bw.write(N - 1 + "\n");
        }

        bw.flush();
        bw.close();
    }
}
