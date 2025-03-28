package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int rows = 3;
        int cols = 3;

        int[][] A = new int[101][101];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            if (A[R - 1][C - 1] == K) {
                System.out.println(time);
                return;
            }

            if (time > 100) {
                System.out.println(-1);
                return;
            }

            if (rows >= cols) { // R 연산
                int maxCol = 0;
                int[][] temp = new int[101][101];

                for (int i = 0; i < rows; i++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int j = 0; j < cols; j++) {
                        if (A[i][j] == 0) continue;
                        map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                    }

                    PriorityQueue<Node> pq = new PriorityQueue<>();

                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        pq.offer(new Node(entry.getKey(), entry.getValue()));
                    }

                    int idx = 0;

                    while (!pq.isEmpty() && idx < 100) {
                        Node node = pq.poll();
                        temp[i][idx++] = node.value;
                        if (idx >= 100) break;
                        temp[i][idx++] = node.occurrence;
                    }

                    maxCol = Math.max(maxCol, idx);
                }

                cols = maxCol;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        A[i][j] = temp[i][j];
                    }
                }
            } else { // C 연산
                int maxRow = 0;
                int[][] temp = new int[101][101];

                for (int j = 0; j < cols; j++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int i = 0; i < rows; i++) {
                        if (A[i][j] == 0) continue;
                        map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                    }

                    PriorityQueue<Node> pq = new PriorityQueue<>();

                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        pq.offer(new Node(entry.getKey(), entry.getValue()));
                    }

                    int idx = 0;

                    while (!pq.isEmpty() && idx < 100) {
                        Node node = pq.poll();
                        temp[idx++][j] = node.value;
                        if (idx >= 100) break;
                        temp[idx++][j] = node.occurrence;
                    }

                    maxRow = Math.max(maxRow, idx);
                }

                rows = maxRow;

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        A[i][j] = temp[i][j];
                    }
                }
            }

            time++;
        }
    }

    private static class Node implements Comparable<Node> {
        int value;
        int occurrence;

        public Node(int value, int occurrence) {
            this.value = value;
            this.occurrence = occurrence;
        }

        @Override
        public int compareTo(Node n) {
            return this.occurrence == n.occurrence
                    ? this.value - n.value
                    : this.occurrence - n.occurrence;
        }
    }
}
