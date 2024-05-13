package queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ28279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Deque<Integer> deque = new ArrayDeque<>();

        int n = parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int command = parseInt(st.nextToken());

            switch (command) {
                case 1:
                    deque.offerFirst(parseInt(st.nextToken()));
                    break;
                case 2:
                    deque.offerLast(parseInt(st.nextToken()));
                    break;
                case 3:
                    if (!deque.isEmpty()) {
                        bw.write(deque.pollFirst() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
                case 4:
                    if (!deque.isEmpty()) {
                        bw.write(deque.pollLast() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
                case 5:
                    bw.write(deque.size() + "\n");
                    break;
                case 6:
                    if (deque.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case 7:
                    if (!deque.isEmpty()) {
                        bw.write(deque.peekFirst() + "\n");
                    } else {
                        bw.write(-1 + "\n");
                    }
                    break;
                case 8:
                    if (!deque.isEmpty()) {
                        bw.write(deque.peekLast() + "\n");
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
