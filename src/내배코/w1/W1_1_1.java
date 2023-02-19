package 내배코.w1;

import java.util.*;

public class W1_1_1 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int pos = 1; // 내 위치 (몇 동에 있는지)
        int stationsIdx = 0; // stations 배열에서 현재 인덱스

        // 아파트 동 끝까지 가는 동안 루프
        while (pos <= n) {
            // 기지국의 좌측 전파 범위와 내 위치 비교해보고, 전파 범위 안에 있다면
            if (stationsIdx < stations.length && pos >= stations[stationsIdx] - w) {
                // 기지국의 우측 전파 범위 밖으로 이동
                pos = stations[stationsIdx] + w + 1;
                stationsIdx++;
            } else {
                // 전파 범위 밖이므로 기지국 신규 설치
                answer++;
                // 내가 설치한 기지국이 커버하는 범위 밖의 다음 동으로 이동
                pos += 2 * w + 1;
            }
        }

        return answer;
    }

    /*
    Queue를 이용한 풀이 (-> 효율성 테스트 시간 초과)

    시간 초과가 발생하면 의심해볼 수 있는 것 3가지:
    1. 루프 (너무 큰 경우의 수를 모두 돌고 있나)
    2. 자료 구조의 적절성
    3. 객체나 Wrapper 타입 (최대한 기본형을 사용하는게 빠름)

    여기서는 루프 내에서 pos를 건너뛰며 이미 루프를 최적화했기 때문에 1번은 아님
    Queue와 Integer 타입을 사용한 것이 속도 저하의 원인으로 보고 폐기
    Queue가 들어간 부분을 stations 배열의 인덱스 변수를 따로 두어 순회하는 걸로 대체
     */
    public int solution2(int n, int[] stations, int w) {
        int answer = 0;
        int pos = 1;

        Queue<Integer> queue = new LinkedList<>();
        for (int s : stations) {
            queue.offer(s);
        }

        while (pos <= n) {
            if (!queue.isEmpty() && pos >= queue.peek() - w) {
                pos = queue.poll() + w + 1;
            } else {
                answer++;
                pos += 2 * w + 1;
            }
        }

        return answer;
    }
}
