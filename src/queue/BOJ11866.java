package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        if (n == 1) {
            System.out.println("<" + 1 + ">");
            return;
        }

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        if (k == 1) {
            System.out.println(queue.toString().replace('[', '<').replace(']', '>'));
            return;
        }

        List<Integer> jp = new ArrayList<>();

        while (!queue.isEmpty()) {
            for (int i = 1; i <= k; i++) {
                int polled = queue.poll();

                if (i == k) {
                    jp.add(polled);
                    break;
                }

                queue.offer(polled);
            }
        }

        System.out.println(jp.toString().replace('[', '<').replace(']', '>'));
    }

    // 백준에서 본 다른 풀이
    public static void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        StringBuilder jp = new StringBuilder("<");

        List<Integer> people = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int idx = 0;

        while (!people.isEmpty()) {
            idx = (idx + k - 1) % people.size();

            jp.append(people.get(idx));

            if (people.size() > 1) {
                jp.append(", ");
            }

            people.remove(idx);
        }

        System.out.println(jp.append(">"));
    }
}
