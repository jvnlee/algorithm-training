package programmers.high_score_kit.greedy;

import java.util.*;

public class PRG42883 {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        int targetLength = number.length() - k;

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            while (!stack.isEmpty() && c > stack.peek() && k > 0) {
                stack.pop();
                k--;
            }

            stack.push(c);
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < targetLength; i++) {
            answer.append(stack.get(i));
        }

        return answer.toString();
    }
}
