package programmers;

import java.util.*;

public class PRG64065 {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        s = s.replace("},{", "/");

        List<List<Integer>> lists = new ArrayList<>();

        String[] tuples = s.split("/");

        for (String tuple : tuples) {
            String[] t = tuple.split(",");
            List<Integer> list = new ArrayList<>();
            for (String num : t) {
                list.add(Integer.parseInt(num));
            }
            lists.add(list);
        }

        lists.sort(Comparator.comparingInt(List::size));

        List<Integer> ans = new ArrayList<>();

        for (List<Integer> l : lists) {
            l.removeAll(ans);
            ans.add(l.get(0));
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }
}
