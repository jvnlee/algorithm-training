package programmers;

import java.util.*;

public class PRG17680 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> cache = new ArrayDeque<>(cacheSize);

        // cache 크기가 0이면 항상 miss
        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        for (String city : cities) {
            String c = city.toLowerCase();

            if (cache.contains(c)) {
                // hit: 실행 시간 + 1
                answer += 1;
                // cache에서 해당 원소를 맨 뒤로 이동시킴
                cache.remove(c);
                cache.add(c);
            } else {
                // miss: 실행 시간 + 5
                answer += 5;
                // cache의 맨 뒤에 해당 원소를 삽입함
                // 이 때, cache가 가득차있다면 맨 앞의 원소를 제거하고 삽입함
                if (cache.size() == cacheSize) {
                    cache.poll();
                    cache.add(c);
                } else {
                    cache.add(c);
                }
            }
        }

        return answer;
    }
}
