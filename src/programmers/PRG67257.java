package programmers;

import java.util.*;
import java.util.regex.*;

public class PRG67257 {
    public long solution(String expression) {
        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        String[] priority = {"*+-", "*-+", "+-*", "+*-", "-*+", "-+*"};

        Pattern p1 = Pattern.compile("[0-9]+");
        Matcher m1 = p1.matcher(expression);
        while (m1.find()) {
            numbers.add(Long.parseLong(m1.group()));
        }

        Pattern p2 = Pattern.compile("[+\\-*]");
        Matcher m2 = p2.matcher(expression);
        while (m2.find()) {
            operators.add(m2.group().charAt(0));
        }

        long max = 0;

        for (String p : priority) {
            List<Long> nc = new ArrayList<>(numbers);
            List<Character> oc = new ArrayList<>(operators);

            for (char c : p.toCharArray()) {
                for (int i = 0; i < oc.size();) {
                    char op = oc.get(i);
                    if (op != c) {
                        i++;
                        continue;
                    }
                    if (op == '*') nc.set(i, nc.get(i) * nc.get(i + 1));
                    else if (op == '+') nc.set(i, nc.get(i) + nc.get(i + 1));
                    else if (op == '-') nc.set(i, nc.get(i) - nc.get(i + 1));

                    nc.remove(i + 1);
                    oc.remove(i);
                }
            }

            max = Math.max(max, Math.abs(nc.get(0)));
        }

        return max;
    }
}
