package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        lis[0] = a[0]; // LIS에 첫번째 원소 추가
        int length = 1; // LIS의 현재 길이 1

        for (int i = 1; i < n; i++) {
            int currentNum = a[i];

            if (currentNum > lis[length - 1]) {
                // 현재 숫자가 LIS의 마지막 원소보다 크다면, LIS에 삽입
                length++;
                lis[length - 1] = currentNum;
            } else {
                // 현재 숫자가 LIS의 마지막 원소보다 작거나 같다면, LIS에 있는 숫자 중에서 현재 숫자보다 큰 값 중 최소값을 찾아 덮어쓰기
                // 이분 탐색으로 탐색 범위를 효율적으로 좁혀 나감
                int lo = 0;
                int hi = length - 1;

                while (lo <= hi) {
                    int mid = (lo + hi) / 2;

                    // 집어낸 LIS 숫자가 현재 숫자보다 작다면 탐색 범위를 더 큰 쪽으로 이동
                    if (lis[mid] < currentNum) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }

                lis[lo] = currentNum;
            }
        }

        System.out.println(length);
    }
}
