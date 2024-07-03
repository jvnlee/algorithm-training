package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// HashMap 방식: O(N)
// 이분 탐색 방식: O(NlogN)
public class BOJ10816 {
    private static int n;
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] targets = new int[m];

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
            answer.append(upperBound(targets[i]) - lowerBound(targets[i])).append(" ");
        }

        System.out.println(answer);
    }

    private static int lowerBound(int target) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target <= cards[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int upperBound(int target) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target < cards[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
