package programmers;

import java.util.*;

public class PRG76502 {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb1 = new StringBuilder(s.substring(0, i));
            StringBuilder sb2 = new StringBuilder(s.substring(i));

            if (check(sb2.append(sb1).toString())) answer++;
        }

        return answer;
    }

    public boolean check(String s) {
        char[] arr = s.toCharArray();
        if (arr[0] == ')' || arr[0] == ']' || arr[0] == '}') return false;

        Stack<Character> stack = new Stack<>();

        for (char c : arr) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    char peek = stack.peek();
                    if ((c == ')' && peek == '(') || (c == ']' && peek == '[') || (c == '}' && peek == '{')) stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
