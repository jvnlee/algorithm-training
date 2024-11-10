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
            if (input == null || input.equals("0 0")) break;
            
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

    private static boolean isTree(int node, int parent) {
        visited[node] = true;
        nodeCount++;

        for (int adj : graph.get(node)) {
            if (adj != parent) {
                if (visited[adj]) {
                    return false;
                }
                if (!isTree(adj, node)) {
                    return false;
                }
            }
        }

        edgeCount += graph.get(node).size();
        
        if (parent == -1) {
            return edgeCount/2 == nodeCount - 1;
        }
        
        return true;
    }
}