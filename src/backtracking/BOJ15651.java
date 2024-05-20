package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15651 {
    public static int[] nums, result;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        result = new int[m];

        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        repeatablePermutation(n, m, 0);

        System.out.println(sb);
    }

    private static void repeatablePermutation(int n, int m, int depth) {
        if (depth == m) {
            String s = Arrays.toString(result).replaceAll("[\\[\\],]", "");
            sb.append(s).append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            result[depth] = nums[i];
            repeatablePermutation(n, m, depth + 1);
        }
    }
}
