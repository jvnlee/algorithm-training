package programmers;

import java.util.*;

public class PRG67258 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        // 보석 종류 파악
        Set<String> set = new HashSet<>();

        for (String gem : gems) {
            set.add(gem);
        }

        int kind = set.size(); // 보석 종류

        // 투포인터로 구간 탐색
        int left = 0;
        int right = 0;
        int length = Integer.MAX_VALUE;  // 최단 구간의 길이

        Map<String, Integer> gemCounts = new HashMap<>(); // (보석 이름, 현재 구간 안에서 해당 보석의 개수)
        Set<String> gemBag = new HashSet<>(); // 현재 구간 안에서 모아진 보석 종류 현황

        while (right < gems.length) {
            gemCounts.put(gems[right], gemCounts.getOrDefault(gems[right], 0) + 1);
            gemBag.add(gems[right]);

            while (gemCounts.get(gems[left]) > 1) {
                gemCounts.replace(gems[left], gemCounts.get(gems[left]) - 1);
                left++;
            }

            if (gemBag.size() == kind && right - left < length) {
                answer[0] = left + 1;
                answer[1] = right + 1;
                length = right - left;
            }

            right++;
        }

        return answer;
    }
}
