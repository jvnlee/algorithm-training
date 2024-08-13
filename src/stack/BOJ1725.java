package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] histogram = new int[n];

        for (int i = 0; i < n; i++) {
            histogram[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int height = histogram[stack.pop()];
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                answer = Math.max(answer, height * width);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int height = histogram[stack.pop()];
            int width = stack.isEmpty()
                    ? histogram.length
                    : histogram.length - stack.peek() - 1;

            answer = Math.max(answer, height * width);
        }

        System.out.println(answer);
    }
}
