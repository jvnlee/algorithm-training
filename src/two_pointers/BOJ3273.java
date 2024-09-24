package two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int x = Integer.parseInt(br.readLine());
        int answer = 0;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum > x) {
                right--;
            } else if (sum == x) {
                answer++;
                left++;
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answer);
    }
}
