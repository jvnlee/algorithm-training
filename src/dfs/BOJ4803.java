package dfs;

import java.io.*;
import java.util.*;

public class BOJ4803 {
    private static int n;
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int nodeCount, edgeCount;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        int caseNum = 1;

        while (true) {
            input = br.readLine();

            if (input == null || input.equals("0 0")) {
                break;
            }
            
            StringTokenizer st = new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            visited = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int trees = getTrees();

            bw.write("Case " + caseNum + ": ");

            if (trees == 0) {
                bw.write("No trees.\n");
            } else if (trees == 1) {
                bw.write("There is one tree.\n");
            } else {
                bw.write("A forest of " + trees + " trees.\n");
            }
            
            caseNum++;
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    // 모든 정점을 한번씩만 방문하며 해당 정점을 시작점(루트)으로 하는 서브 트리의 개수를 셈
    private static int getTrees() {
        int treeCount = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                nodeCount = 0;
                edgeCount = 0;

                if (isTree(i, -1)) {
                    treeCount++;
                }
            }
        }

        return treeCount;
    }

    /*
    그래프가 주어졌을 때, 트리로 판정하기 위해서는
    1. 사이클이 있어서는 안됨
    2. 간선의 개수 = 정점의 개수 - 1 을 만족해야함
     */
    private static boolean isTree(int node, int parent) {
        visited[node] = true;
        nodeCount++;

        for (int adj : graph.get(node)) {
            if (adj != parent) { // 부모가 아닌 인접 노드일 때
                if (visited[adj]) { // 이미 방문했던 기록이 있다면 중복 방문하게 된 것이므로 사이클을 암시
                    return false;
                }

                if (!isTree(adj, node)) { // 하위 노드를 탐색하는 과정에서 트리 조건이 만족되지 않는 경우
                    return false;
                }
            }
        }

        edgeCount += graph.get(node).size();
        
        if (parent == -1) { // 루트 노드에 대해서만 다음 검증 과정을 거침 (모든 하위 노드 탐색을 마친 후)
            return edgeCount / 2 == nodeCount - 1; // edge는 양방향성이라 모두 2번씩 세었으므로 edgeCount는 2로 나눔
        }
        
        return true;
    }
}