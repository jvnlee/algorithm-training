package 내배코;

import java.util.*;

public class W1_4_1 {
    /*
    단일 루프만 사용한 풀이

    solution2의 개선안 (효율성 테스트 100ms 미만)
     */
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int indexB = B.length - 1;

        for (int i = A.length - 1; i >= 0; i--) {
            // B의 최대값보다 작은 A의 숫자중에서 가장 큰 값을 찾기
            if (B[indexB] > A[i]) {
                indexB--; // 인덱스 내림 (B의 숫자 하나 소모)
                answer++; // 승점 추가
            }
        }

        return answer;
    }

    /*
    이중 루프가 사용된 풀이

    이렇게 해도 효율성 테스트까지 전부 통과는 함 (100ms 이상)
    그런데 만약 시간 제한이 더 빡빡하다면 루프를 한겹만 쓰도록 리팩토링할 필요가 있음(위 풀이)
     */
    public int solution2(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = B.length - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (B[i] > A[j] && A[j] != 0) {
                    A[j] = 0;
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
