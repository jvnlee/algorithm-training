package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] heights = new int[n];

        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        long answer = 0;

        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int count = 1;

            while (!stack.isEmpty() && stack.peek()[0] <= heights[i]) {
                answer += stack.peek()[1];
                if (stack.peek()[0] == heights[i]) {
                    count += stack.pop()[1];
                } else {
                    stack.pop();
                }
            }

            if (!stack.isEmpty()) {
                answer++;
            }

            stack.push(new int[]{heights[i], count});
        }

        System.out.println(answer);
    }
}
