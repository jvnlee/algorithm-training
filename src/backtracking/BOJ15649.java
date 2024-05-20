package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15649 {
    public static int[] nums, result;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        result = new int[m];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        permutation(n, m, 0); // nPm

        System.out.println(sb);
    }

    private static void permutation(int n, int m, int depth) {
        if (depth == m) {
            String s = Arrays.toString(result).replaceAll("[\\[\\],]", "");
            sb.append(s).append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = nums[i];
                permutation(n, m, depth + 1);
                visited[i] = false;
            }
        }
    }
}
