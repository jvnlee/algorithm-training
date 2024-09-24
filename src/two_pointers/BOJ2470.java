package two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fluids = new int[N];

        for (int i = 0; i < N; i++) {
            fluids[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fluids);

        int left = 0;
        int right = N - 1;

        int bestLeft = 0;
        int bestRight = 0;

        int min = Integer.MAX_VALUE;

        while (left < right) {
            int sum = fluids[left] + fluids[right];

            if (sum == 0) {
                bestLeft = left;
                bestRight = right;
                break;
            }

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                bestLeft = left;
                bestRight = right;
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(fluids[bestLeft] + " " + fluids[bestRight]);
    }
}
