package programmers;

import java.util.Arrays;

public class PRG64062 {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt();

        // 이분 탐색으로 징검다리를 건널 수 있는 최대 인원 수 찾기
        while (left <= right) {
            int mid = (left + right) / 2;

            if (canCross(stones, k, mid)) {
                left = mid + 1; // 인원 늘리기
            } else {
                right = mid - 1; // 인원 줄이기
            }
        }

        return right;
    }

    private boolean canCross(int[] stones, int k, int people) {
        int count = 0; // 현재 목표 인원을 수용할 수 없는 연속된 돌의 개수 (k개보다 많다면 인원 수용 불가)

        for (int stone : stones) {
            if (stone - people < 0) {
                count++;
            } else {
                count = 0;
            }

            if (count >= k) {
                return false; // 돌의 개수가 k개 이상이 되는 순간 인원 수용 불가 판정
            }
        }

        return true;
    }
}
