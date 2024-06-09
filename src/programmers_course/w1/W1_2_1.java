package programmers_course.w1;

import java.util.*;
import java.util.stream.*;

public class W1_2_1 {
    // Stream을 사용해 간결하게 만든 풀이
    public String solution(int[] numbers) {
        String answer = IntStream.of(numbers) // int[] -> IntStream
                .mapToObj(String::valueOf) // IntStream -> Stream<String>
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2)) // 원소 정렬
                .collect(Collectors.joining()); // 스트림 내의 모든 원소를 문자열로 합치기

        if (answer.startsWith("0")) return "0";

        return answer;
    }

    // 일반적인 풀이
    public String solution2(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        String[] strNums = new String[numbers.length];

        for (int i = 0; i < strNums.length; i++) {
            strNums[i] = "" + numbers[i];
        }

        /*
        두 요소를 이어붙여보고 붙였을 때 더 큰 값이 되도록 배열
        (s2 + s1)이 더 크면 s2, s1 순으로 배열하고, 더 작으면 s1, s2 순으로 배열
         */
        Arrays.sort(strNums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        // StringBuilder를 사용하는 것이 빠름
        for (String s : strNums) {
            sb.append(s);
        }

        String answer = sb.toString();

        // 문제 조건에서 주어진 숫자들이 전부 0이면 "000..00" 말고 그냥 "0 "반환
        if (answer.startsWith("0")) return "0";

        return answer;
    }
}
