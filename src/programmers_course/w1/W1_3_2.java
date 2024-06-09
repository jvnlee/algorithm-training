package programmers_course.w1;

public class W1_3_2 {
    public long solution(int goal, int[] durations) {
        int maxDuration = 0;
        for (int d : durations) maxDuration = Math.max(maxDuration, d); // 작업이 가장 오래 걸리는 사람의 작업 시간 구하기

        long finishHours = getFinishHours(goal, durations, maxDuration);
        return getTotalIncentives(finishHours, durations, maxDuration);
    }

    /*
    목표량 만큼 생산하는데 걸리는 시간 구하기 (이분 탐색)

    이분 탐색은 초기 구간을 정하고(min ~ max) 최적의 값을 중간 값(mid)으로 가정한 다음에
    결과에 따라 구간의 절반인 min ~ mid 구간 또는 (mid + 1) ~ max 구간을 같은 방법으로 재탐색하는 방식.
     */
    public long getFinishHours(int goal, int[] durations, int maxDuration) {
        long min = 0;
        // 최대 시간: 모두가 인당 할당량(소수점 고려해서 +1개)을 가장 작업 시간이 긴 사람의 속도로 만드는 경우
        long max = maxDuration * (goal / durations.length + 1L);

        // min과 max가 수렴할 때까지 반복 (min이 높아져서 max와 만나든, max가 낮아져서 min과 만나든)
        while (min < max) {
            long mid = (min + max) / 2; // 예상 소요시간을 중간 값으로 설정

            int made = 0; // 총 생산량

            for (int d : durations) made += mid / d; // 예상 총 소요시간 동안 각자가 생산한 양을 누적

            // 총 생산량이 목표량보다 적다면, 예상 소요 시간이 부족했다는 의미이므로 min을 올림 (upper-half 구간으로 재탐색)
            if (made < goal) min = mid + 1;
            // 총 생산량이 목표량보다 많다면, 예상 소요 시간이 넉넉했다는 의미이므로 max를 낮춤 (lower-half 구간으로 재탐색)
            else max = mid;
        }

        return max;
    }

    // 지급해야할 총 인센티브 계산하기
    public long getTotalIncentives(long finishHours, int[] durations, int maxDuration) {
        long min = finishHours / maxDuration; // 최소 생산자의 생산량

        long exceed = 0;

        for (int d : durations) {
            exceed += finishHours / d - min; // 초과 생산량 누적
        }

        return exceed * 10000L;
    }
}
