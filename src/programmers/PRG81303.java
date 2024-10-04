package programmers;

import java.util.*;

public class PRG81303 {
    public String solution(int n, int k, String[] cmd) {
        TreeSet<Integer> table = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            table.add(i);
        }

        Stack<Integer> bin = new Stack<>();

        int cursor = k;

        for (String c : cmd) {
            if (c.startsWith("U")) {
                int amount = Integer.parseInt(c.split(" ")[1]);

                while (amount-- > 0) {
                    cursor = table.lower(cursor);
                }
            } else if (c.startsWith("D")) {
                int amount = Integer.parseInt(c.split(" ")[1]);

                while (amount-- > 0) {
                    cursor = table.higher(cursor);
                }
            } else if (c.startsWith("C")) {
                bin.push(cursor);
                table.remove(cursor);

                if (table.higher(cursor) != null) {
                    cursor = table.higher(cursor);
                } else {
                    cursor = table.lower(cursor);
                }
            } else {
                table.add(bin.pop());
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (table.contains(i)) {
                answer.append("O");
            } else {
                answer.append("X");
            }
        }

        return answer.toString();
    }
}
