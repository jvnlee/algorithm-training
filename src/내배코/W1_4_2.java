package 내배코;

import java.util.*;
import java.util.stream.*;

public class W1_4_2 {
    public int solution(int[] coin, int k) {
        if (coin.length == 1) return 0; // 동전이 1개면 뒤집을 필요 없음

        int sum = IntStream.of(coin).sum();

        // 동전이 모두 0이거나 모두 1이면 뒤집을 필요 없음
        if (sum == 0 || sum == coin.length) return 0;
        // 불가능한 케이스 (이걸 파악해서 걸러주는게 좀 까다로운듯, 그런데 이 부분을 빼도 정답 처리됨)
        if (sum != k && coin.length == k) return -1;

        int makeAll0 = getFlipCount(Arrays.copyOf(coin, coin.length), k, 0);
        int makeAll1 = getFlipCount(Arrays.copyOf(coin, coin.length), k, 1);
        int answer = Math.min(makeAll0, makeAll1);

        // 0으로 만들기, 1로 만들기 둘다 실패한 경우
        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    // 뒤집기 수행 횟수 구하기 (type은 만들고자 하는 면의 종류 = 0 또는 1)
    public int getFlipCount(int[] coin, int k, int type) {
        int flipCount = 0;

        // 맨 앞부터 시작해서 뒤집을 수 있는 횟수는 최대 (coin.length - k + 1)
        for (int i = 0; i < coin.length - k + 1; i++) {
            if (coin[i] == type) continue; // 현재 동전이 내가 만들고자 하는 면이면 뒤집을 필요 없음

            // 현재 동전부터 k개 뒤집기
            for (int j = 0; j < k; j++) {
                coin[i + j] = 1 - coin[i + j];
            }

            flipCount++;
        }

        int typeCheckCount = 0;

        // 각 동전이 내가 만들고자 하는 면이면 카운트에 누적
        for (int c : coin) {
            if (c == type) typeCheckCount++;
        }

        // 모든 동전이 내가 만들고자 하는 면이 되었으면 뒤집기 횟수 반환
        if (typeCheckCount == coin.length) return flipCount;
        // 실패한 경우에는 정수형 최대치 반환
        return Integer.MAX_VALUE;
    }
}
