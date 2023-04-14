package programmers;

import java.util.*;

public class PRG60058 {
    public String solution(String p) {
        if (p.isEmpty()) return "";
        if (isCorrect(p)) return p;

        return algorithm(p);
    }

    public boolean isBalanced(String p) {
        List<Character> open = new ArrayList<>();
        List<Character> close = new ArrayList<>();
        for (char c : p.toCharArray()) {
            if (c == '(') {
                open.add(c);
            } else {
                close.add(c);
            }
        }
        return open.size() == close.size();
    }

    public boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();

        if (p.charAt(0) == ')') return false; // 첫 문자가 ')'이면 더 확인할 필요 없이 올바르지 않은 문자열

        for (char c : p.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty()) return false; // "()))" 같은 경우처럼 '('가 먼저 바닥나면 올바르지 않은 문자열
                stack.pop();
            }
        }

        return stack.size() == 0;
    }

    public String switchParentheses(String p) {
        p = p.replaceAll("\\(", "t");
        p = p.replaceAll("\\)", "(");
        p = p.replaceAll("t", ")");
        return p;
    }

    public String sliceFirstAndLast(String p) {
        return p.substring(1, p.length() - 1);
    }

    public String algorithm(String p) {
        StringBuilder reformed = new StringBuilder();

        for (int i = 2; i <= p.length(); i += 2) {
            String u = p.substring(0, i);
            String v = p.substring(i);

            if (isBalanced(u)) {
                if (isCorrect(u)) {
                    reformed.append(u);
                    reformed.append(algorithm(v));
                    break;
                } else {
                    reformed.append("(");
                    reformed.append(algorithm(v));
                    reformed.append(")");

                    String switched = switchParentheses(sliceFirstAndLast(u));
                    reformed.append(switched);
                    break;
                }
            }
        }

        return reformed.toString();
    }
}
