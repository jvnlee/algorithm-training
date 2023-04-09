package programmers;

import java.util.*;

public class PRG17684 {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();

        List<String> dict = new ArrayList<>();
        dict.add("");

        char x = 'A';

        // dict 초기화
        for (int i = 0; i < 26; i++) {
            dict.add(String.valueOf(x));
            x += 1;
        }

        for (int i = 0; i < msg.length(); i++) {
            StringBuilder w = new StringBuilder(String.valueOf(msg.charAt(i)));

            if (i == msg.length() - 1) {
                answer.add(dict.indexOf(w.toString()));
                break;
            }

            String c = String.valueOf(msg.charAt(i + 1));

            // w + c가 dict에 없을 때까지 w를 계속 갱신
            while (dict.contains(w + c)) {
                w.append(c);

                i++;
                if (i == msg.length() - 1) break;

                c = String.valueOf(msg.charAt(i + 1));
            }

            dict.add(w + c);
            answer.add(dict.indexOf(w.toString()));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
