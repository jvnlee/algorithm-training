package bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[10000];

            visited[A] = true;
            queue.offer(new Node(A, ""));

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                int num = cur.num;
                String seq = cur.seq;

                // D
                int numD = (num * 2) % 10000;

                if (numD == B) {
                    bw.write(seq + "D\n");
                    break;
                } else {
                    if (!visited[numD]) {
                        visited[numD] = true;
                        queue.offer(new Node(numD, seq + "D"));
                    }
                }

                // S
                int numS = num == 0 ? 9999 : num - 1;

                if (numS == B) {
                    bw.write(seq + "S\n");
                    break;
                } else {
                    if (!visited[numS]) {
                        visited[numS] = true;
                        queue.offer(new Node(numS, seq + "S"));
                    }
                }

                // L
                int numL = (num % 1000) * 10 + num / 1000;

                if (numL == B) {
                    bw.write(seq + "L\n");
                    break;
                } else {
                    if (!visited[numL]) {
                        visited[numL] = true;
                        queue.offer(new Node(numL, seq + "L"));
                    }
                }

                // R
                int numR = (num % 10) * 1000 + num / 10;

                if (numR == B) {
                    bw.write(seq + "R\n");
                    break;
                } else {
                    if (!visited[numR]) {
                        visited[numR] = true;
                        queue.offer(new Node(numR, seq + "R"));
                    }
                }
            }
        }

        bw.flush();
        bw.close();
    }

    private static class Node {
        int num;
        String seq;

        public Node(int num, String seq) {
            this.num = num;
            this.seq = seq;
        }
    }
}
