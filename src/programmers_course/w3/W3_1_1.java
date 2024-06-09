package programmers_course.w3;

import java.util.*;

public class W3_1_1 {
    public int solution1(String[][] clothes) {
        Map<String, Integer> typeCounts = new HashMap<>();

        for (String[] c : clothes) {
            String type = c[1];
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
            // type에 대한 count 값이 존재하지 않으면 0을 기본값으로 하고, count 값에 1을 더함
        }

        int answer = 1; // 곱 연산을 할거라서 1로 세팅

        for (Integer count : typeCounts.values()) {
            answer *= (count + 1); // 해당 type의 옷을 안 입는 경우의 수 1을 포함
        }

        return answer - 1; // 모든 type의 옷을 안 입는 경우의 수 1을 제외
    }

    /*
    1. HashMap을 사용한 풀이
    테스트 케이스 평균 소요 시간 (0.0x ms)
    Map의 메서드 중 getOrDefault()는 주어진 key에 대해 value가 있으면 value를 반환하고, 없으면 세팅한 초기값을 반환해줌
     */

    public int solution2(String[][] clothes) {
        int answer = Arrays.stream(clothes)
                .map(cloth -> cloth[1]) // 옷의 타입만 추출
                .distinct() // 중복 제거
                .map(type -> (int) Arrays.stream(clothes).filter(cloth -> cloth[1].equals(type)).count())
                // 새 clothes 스트림을 만들고 타입과 일치하는 옷의 개수를 셈 (count 반환 타입이 long이므로 int로 캐스팅)
                .map(count -> count + 1) // 각 타입마다 안 입는 경우의 수 1씩 추가
                .reduce(1, (cur, next) -> cur * next); // 누적 곱 구하기

        return answer - 1; // 모든 type의 옷을 안 입는 경우의 수 1을 제외
    }

    /*
    2. Stream을 사용한 풀이
    테스트 케이스 평균 소요 시간 (2 ~ 4 ms)
    동일 실행환경에서 solution1에 비해 시간 소모가 크지만, 코드 간결성을 극대화한 풀이.
     */




}
