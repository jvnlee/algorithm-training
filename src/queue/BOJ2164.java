package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            deque.offerLast(i);
        }

        if (deque.size() == 1) {
            System.out.println(deque.peekFirst());
            return;
        }

        while (true) {
            deque.removeFirst();

            if (deque.size() == 1) {
                System.out.println(deque.peekFirst());
                break;
            }

            int card = deque.pollFirst();
            deque.offerLast(card);
        }
    }
}
