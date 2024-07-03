package binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
    private static int n;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a); // 이분 탐색을 위해 오름차순 정렬

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());

            if (binarySearch(b[i])) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
    }

    // 전체 탐색: O(N)
    // 이분 탐색: O(logN)
    // Arrays.binarySearch()가 동일한 기능을 제공함
    private static boolean binarySearch(int target) {
        int left = 0; // 좌측 포인터
        int right = n - 1; // 우측 포인터

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == a[mid]) {
                return true;
            }

            if (target < a[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
