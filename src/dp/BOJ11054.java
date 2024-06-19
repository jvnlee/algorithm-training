package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // Longest Increasing Subsequence
        // i에서 끝나는 가장 긴 증가하는 부분 수열
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        // Longest Decreasing Subsequence
        // i에서 시작하는 가장 긴 감소하는 부분 수열
        int[] lds = new int[n];
        Arrays.fill(lds, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // Longest Bitonic Subsequence
        // LIS와 LDS를 합하고, 각 부분 배열의 시작 인덱스가 중복 카운트 되므로 1을 뺀 값
        int[] lbs = new int[n];

        for (int i = 0; i < n; i++) {
            lbs[i] = lis[i] + lds[i] - 1;
        }

        Arrays.sort(lbs);

        System.out.println(lbs[n - 1]);
    }
}
