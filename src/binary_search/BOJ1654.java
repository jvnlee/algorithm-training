package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lans = new int[k];

        long max = 0L; // 가진 랜선에서 가능한 최대 길이

        for (int i = 0; i < k; i++) {
            lans[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lans[i]);
        }

        long min = 1L; // 가진 랜선에서 가능한 최소 길이

        while (min <= max) {
            long mid = (min + max) / 2; // mid 길이 단위로 잘라보자

            long count = 0L;

            for (int i = 0; i < k; i++) {
                count += (lans[i] / mid); // mid 길이 단위로 자른 토막의 개수를 세자
            }

            if (count < n) {
                max = mid - 1; // 더 작게 잘라보자
            } else {
                min = mid + 1; // 더 크게 잘라보자
            }
        }

        System.out.println(max);
    }
}
