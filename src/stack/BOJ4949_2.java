package stack;

import java.io.*;
import java.util.Stack;

public class BOJ4949_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = br.readLine();

            if (s.equals(".")) break;

            bw.write(
                    checkBalance(s.toCharArray())
            );
        }

        bw.flush();
        bw.close();
    }

    public static String checkBalance(char[] charArr) {
        Stack<Character> stack = new Stack<>();

        for (char c : charArr) {
            if (c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "no\n";
                }
            } else if (c == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return "no\n";
                }
            }
        }

        return stack.isEmpty() ? "yes\n" : "no\n";
    }
}
