package 내배코.w3;

import java.util.*;

public class W3_1_2 {
    public int solution(int[] bell) {
        int answer = 0;

        for (int i = 0; i < bell.length; i++) {
            if (bell[i] == 2) bell[i] = -1; // 초록색 장식을 -1로 변환
        }

        int total = bell[0]; // 누적합

        Map<Integer, Integer> records = new HashMap<>();
        records.put(0, -1); // 누적합 0은 인덱스 -1로 저장
        records.put(total, 0); // 첫번째 누적합 데이터: bell[0], 0

        for (int i = 1; i < bell.length; i++) {
            total += bell[i];
            if (!records.containsKey(total)) {
                // 기록 안에 현재의 누적합이 없는 경우 신규 저장
                records.put(total, i);
            } else {
                // 있는 경우, 그 누적합이 처음 나왔던 인덱스와 현재 인덱스 사이의 값의 합은 0이므로 균형
                answer = Math.max(answer, i - records.get(total));
            }
        }

        return answer;
    }

    /*
    장식 배열에서는 빨간색이 1, 초록색이 2로 주어지는데, 초록색을 -1로 변환하면 쉽게 접근할 수 있음
    결국 잘랐을 때 두 종류의 개수가 동일하려면 합이 0이면 되기 때문.

    배열의 앞에서부터 누적합을 구해 저장하고, 해당 누적합이 다시 등장하면 그 사이의 합은 0이 되므로 두 인덱스 차가 정답 후보가 됨
    정답 후보 중 최대값이 곧 정답
     */
}
