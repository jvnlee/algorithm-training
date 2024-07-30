package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] counts = new int[10000001];

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            counts[num]++;
            nums[i] = num;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && counts[nums[stack.peek()]] < counts[nums[i]]) {
                nums[stack.pop()] = nums[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            nums[stack.pop()] = -1;
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            answer.append(nums[i]).append(" ");
        }

        System.out.println(answer);
    }
}
