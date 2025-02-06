package programmers.high_score_kit.binary_search;

import java.util.*;

public class PRG43236 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        long lo = 1;
        long hi = distance;
        long answer = 0;

        while (lo <= hi) {
            long mid = (lo + hi) / 2; // 최소 거리 후보

            int removeCount = 0; // 제거한 바위 개수
            long currentPos = 0; // 현위치 (시작점 - 바위 - ... - 바위 - 종점)

            for (int rock : rocks) {
                if (rock - currentPos < mid) { // 최소 거리 후보보다 더 짧은 거리면
                    removeCount++; // 바위 제거
                } else {
                    currentPos = rock; // 바위 보존하고 현위치 갱신
                }
            }

            if (distance - currentPos < mid) { // 마지막 바위와 종점 간 거리도 체크
                removeCount++;
            }

            if (removeCount <= n) { // 제거한 바위 개수가 목표 개수 이하면
                answer = mid; // 정답 만족
                lo = mid + 1; // 더 큰 최소 거리 값으로 탐색
            } else {
                hi = mid - 1; // 더 작은 최소 거리 값으로 탐색
            }
        }

        return (int) answer;
    }
}