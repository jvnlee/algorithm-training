package programmers_course.w1;

import java.util.stream.*;

public class W1_3_1 {
    /*
    스트림을 사용한 풀이 (solution2를 리팩토링함)

    장점: 코드가 간결해짐
    단점: 일반 풀이보다 시간복잡도가 높음 (효율성 테스트 삑사리 남)
     */
    public int solution(int[] budgets, int M) {
        int answer = 0;
        int min = 0;
        int max = IntStream.of(budgets).max().orElse(0);

        while (min <= max) {
            final int mid = (min + max) / 2; // 스트림의 람다 내에서 사용하기 위해서는 final 변수여야함

            int sum = IntStream.of(budgets)
                    .map(b -> Math.min(b, mid)) // 예산과 중간값을 비교해서 더 작은 값 사용
                    .sum(); // 총합 구하기

            if (sum <= M) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    /*
    일반 풀이

    장점: 스트림을 사용한 풀이보다 시간복잡도가 낮음 (효율성 테스트 모두 통과)
     */
    public int solution2(int[] budgets, int M) {
        int answer = 0;
        int min = 0;
        int max = 0;

        // 적절한 상한액을 찾기 위해 지방 예산의 최소액(0)과 최대액를 설정
        for (int b : budgets) {
            if (b > max) max = b;
        }

        // min과 max가 수렴할 때 최적의 상한액이 구해짐
        while (min <= max) {
            // 중간값을 상한액으로 설정
            int mid = (min + max) / 2;

            int sum = 0;

            for (int b : budgets) {
                if (b > mid) sum += mid;
                else sum += b;
            }

            // 현재의 상한액으로 구한 지방 예산 총액이 전체 예산보다 적거나 같으면
            if (sum <= M) {
                // 최소액을 종전 중간값보다 1 높은 수치로 재설정 (상향)
                min = mid + 1;
                // 우선 현재의 상한액(중간값)을 정답 후보로 설정
                answer = mid;
            } else {
                // 최대액을 종전 중간값보다 1 낮은 수치로 재설정 (하향)
                max = mid - 1;
            }
        }

        return answer;
    }
}
