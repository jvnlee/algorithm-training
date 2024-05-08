package stack;

import java.util.*;

public class BOJ4949 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;

        while(true) {
            s = scanner.nextLine();

            if (s.equals(".")) break;
            System.out.println(solve(s));
        }

    }

    public static String solve(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[') stack.push(c);
            else if (c == ')') {
                if (stack.empty() || stack.peek() != '(') return "no";
                else stack.pop();
            }
            else if (c == ']') {
                if(stack.empty() || stack.peek() != '[') return "no";
                else stack.pop();
            }
        }

        if (stack.empty()) return "yes";
        else return "no";
    }
}