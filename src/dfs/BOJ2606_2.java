package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class BOJ2606_2 {
    public static int computers, connections, count;
    public static boolean[] infected;
    public static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        computers = parseInt(br.readLine());
        connections = parseInt(br.readLine());
        infected = new boolean[computers + 1];

        // 그래프 초기화
        // 노드 개수 + 1 만큼 내부 리스트 생성 (0번 컴퓨터는 없으니까 하나 더)
        for (int i = 0; i <= computers; i++) {
            graph.add(new ArrayList<>());
        }

        // 연결 정보 추가 (양측 모두)
        for (int i = 0; i < connections; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int comA = parseInt(st.nextToken());
            int comB = parseInt(st.nextToken());

            graph.get(comA).add(comB);
            graph.get(comB).add(comA);
        }

        // 감염 시작
        infect(1);

        System.out.println(count);
    }

    public static void infect(int host) {
        infected[host] = true;
        List<Integer> neighbors = graph.get(host); // 현재 컴퓨터와 연결된 컴퓨터들

        for (int neighbor : neighbors) {
            if (!infected[neighbor]) {
                infect(neighbor); // 감염 처리
                count++;
            }
        }
    }
}
