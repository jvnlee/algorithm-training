package queue;

import java.io.*;
import java.util.*;

public class BOJ18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Deque<Integer> deque = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    deque.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (!deque.isEmpty()) {
                        bw.write(deque.poll() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    if (deque.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "front":
                    if (!deque.isEmpty()) {
                        bw.write(deque.getFirst() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
                case "back":
                    if (!deque.isEmpty()) {
                        bw.write(deque.getLast() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
            }
        }

        bw.flush();
        bw.close();
    }
}
