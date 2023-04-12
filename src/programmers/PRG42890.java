package programmers;

import java.util.*;

public class PRG42890 {
    List<Integer> keys = new ArrayList<>();

    public int solution(String[][] relation) {
        int rowNum = relation.length;
        int columnNum = relation[0].length;

        /*
        비트 연산 활용.
        어떤 column을 key의 요소로 사용하면 1로 표시, 사용하지 않으면 0으로 표시.

        가령 column이 4개라면 k는 0001 이상이고, 10000 이하이므로
        0001 ~ 1111 의 값을 가질 수 있음
         */
        for (int k = 1; k < (1 << columnNum); k++) {
            if (!hasMinimality(k)) continue; // 후보키 k의 최소성 검증

            Set<String> set = new HashSet<>();
            for (String[] row : relation) {
                StringBuilder tuple = new StringBuilder();

                for (int i = 0; i < columnNum; i++) {
                    if (((1 << i) & k) > 0) {
                        tuple.append(row[i]).append("/");
                    }
                }

                // set 안에 중복된 tuple이 있는 경우 false가 반환됨. 이는 k가 후보키의 유일성을 만족하지 못한다는 의미
                if (!set.add(tuple.toString())) break;
            }

            // set 안에 tuple이 모두 담기면 그 개수가 rowNum가 동일해짐. 이러면 k는 후보키로서 유일성을 만족한다는 의미
            if (set.size() == rowNum) keys.add(k);
        }

        return keys.size();
    }

    /*
    새로운 후보키 1011의 최소성 여부를 평가한다고 가정
    만약 이미 0011 같은 후보키가 존재하면 1011은 최소성을 만족하지 못함
    이를 비트 연산으로 확인해볼 수 있음 (0011 & 1011 == 0011)
     */
    public boolean hasMinimality(int candidate) {
        for (int key : keys) {
            if ((key & candidate) == key) return false;
        }
        return true;
    }
}
