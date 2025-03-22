package actual_test;

import java.util.*;

public class Naver250322_Q3 {
    public int solution(String[] fruit, int k) {
        int[] fruitInts = new int[fruit.length];

        for (int i = 0; i < fruit.length; i++) {
            fruitInts[i] = Integer.parseInt(fruit[i], 2);
        }

        Set<Integer> flavors = new HashSet<>();
        combination(fruitInts, flavors, 0, 0, 0, k);

        return flavors.size();
    }

    private void combination(int[] fruitInts, Set<Integer> flavors, int currentMask, int start, int depth, int k) {
        if (depth == k) {
            flavors.add(currentMask);
            return;
        }

        for (int i = start; i < fruitInts.length; i++) {
            combination(fruitInts, flavors, fruitInts[i] | currentMask, i + 1, depth + 1, k);
        }
    }
}

/*
조합 + 비트마스킹 풀이 자체는 금방 떠올렸는데,
2진수 문자열을 2진수로 파싱하는 것과 조합 구현에서 절었음

오답 노트
1. Integer.parseInt()의 2번째 파라미터로 진법을 지정할 수 있다
2. 조합 구현 시 조합 시작 지점인 start 파라미터를 사용해야 중복된 조합을 피할 수 있다
 */