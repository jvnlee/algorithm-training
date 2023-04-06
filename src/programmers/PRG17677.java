package programmers;

import java.util.*;

public class PRG17677 {
    public int solution(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        for (int i = 0; i < (str1.length() - 1); i++) {
            String subStr = str1.substring(i, i + 2).toLowerCase();
            if (subStr.matches(".*[^a-z].*")) continue;
            list1.add(subStr);
        }

        for (int i = 0; i < (str2.length() - 1); i++) {
            String subStr = str2.substring(i, i + 2).toLowerCase();
            if (subStr.matches(".*[^a-z].*")) continue;
            list2.add(subStr);
        }

        if (list1.size() == 0 && list2.size() == 0) return 65536;

        HashSet<String> intersection = new HashSet<>(list1);
        intersection.retainAll(list2);

        ArrayList<String> union = new ArrayList<>(list1);
        union.addAll(list2);
        union.removeAll(intersection);

        int iSize = 0;
        int uSize = union.size();

        for (String s : intersection) {
            iSize += Math.min(Collections.frequency(list1, s), Collections.frequency(list2, s));
            uSize += Math.max(Collections.frequency(list1, s), Collections.frequency(list2, s));
        }

        return (int) ((double) iSize / (double) uSize * 65536);
    }
}
