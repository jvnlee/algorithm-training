package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            Stack<String> stack = new Stack<>();

            String s = br.readLine();
            String[] c = s.split("");

            for (int j = 0; j < s.length(); j++) {
                if (stack.isEmpty()) {
                    stack.push(c[j]);
                } else {
                    if (stack.peek().equals(c[j])) {
                        stack.pop();
                    } else {
                        stack.push(c[j]);
                    }
                }
            }

            if (stack.isEmpty()) count++;
        }

        System.out.println(count);
    }
}
