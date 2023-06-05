package programmers;

import java.util.*;

public class PRG49993 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= skill.length(); i++) {
            list.add(skill.substring(0, i));
        }

        String regex = "[^" + skill + "]";

        for (String st : skill_trees) {
            st = st.replaceAll(regex, "");
            if (list.contains(st) || st.isEmpty()) answer++;
        }

        return answer;
    }
}
