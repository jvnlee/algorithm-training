package prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] temps = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            temps[i] = Integer.parseInt(st.nextToken());
        }

        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += temps[i];
        }

        int maxSum = currentSum;

        for (int i = k; i < n; i++) {
            currentSum += temps[i] - temps[i - k]; // 다음 숫자 하나 더하고, 이전 숫자 하나 빼기 (합 범위를 한칸씩 이동)
            maxSum = Math.max(maxSum, currentSum);
        }

        System.out.println(maxSum);
    }
}
