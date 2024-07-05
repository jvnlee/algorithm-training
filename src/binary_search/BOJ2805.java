package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] trees = new int[n];

        st = new StringTokenizer(br.readLine());

        long max = 0L;

        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        long min = 1L;

        while (min <= max) {
            long mid = (min + max) / 2;
            long totalCut = 0L;

            for (int i = 0; i < n; i++) {
                long cut = trees[i] - mid;
                if (cut > 0) {
                    totalCut += cut;
                }
            }

            if (totalCut < m) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
