package two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1450 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] stuffs = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            stuffs[i] = Integer.parseInt(st.nextToken());
        }

        int[] firstHalf = Arrays.copyOfRange(stuffs, 0, N / 2);
        int[] secondHalf = Arrays.copyOfRange(stuffs, N / 2, N);

        List<Long> firstHalfSubsetSums = getSubsetSums(firstHalf);
        List<Long> secondHalfSubsetSums = getSubsetSums(secondHalf);

        Collections.sort(secondHalfSubsetSums);

        int answer = 0;

        for (long f : firstHalfSubsetSums) {
            answer += countValidSubsets(secondHalfSubsetSums, C - f); // C에서 firstHalf의 부분합을 뺀 용량에 채율 수 있는 secondHalf의 부분합 개수 세기
        }

        System.out.println(answer);
    }

    private static int countValidSubsets(List<Long> subsetSums, long availableCapacity) {
        int left = 0;
        int right = subsetSums.size() - 1;
        int count = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (subsetSums.get(mid) <= availableCapacity) {
                count = mid + 1; // index가 mid 이상인 부분합들은 모두 유효
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return count;
    }

    private static List<Long> getSubsetSums(int[] arr) {
        List<Long> subsetSums = new ArrayList<>();
        int len = arr.length;

        for (int i = 0; i < (1 << len); i++) {
            long sum = 0;

            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }

            subsetSums.add(sum);
        }

        return subsetSums;
    }
}
