package divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 분할 정복 방식: O(NlogN)
// 스택 방식: O(N) (BOJ6549_STACK 참조)
public class BOJ6549_DNC {
    private static int[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            histogram = new int[n];

            for (int i = 0; i < n; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(divideAndConquer(0, n - 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static long divideAndConquer(int start, int end) {
        if (start == end) {
            return histogram[start];
        }

        int mid = (start + end) / 2;

        long leftRangeMax = divideAndConquer(start, mid);
        long rightRangeMax = divideAndConquer(mid + 1, end);

        long totalRangeMax = getTotalRangeMax(start, mid, end);

        return Math.max(Math.max(leftRangeMax, rightRangeMax), totalRangeMax);
    }

    private static long getTotalRangeMax(int start, int mid, int end) {
        int leftPointer = mid;
        int rightPointer = mid;

        long minHeight = histogram[mid];
        long maxArea = minHeight;

        while (start < leftPointer && rightPointer < end) {
            if (histogram[leftPointer - 1] < histogram[rightPointer + 1]) {
                rightPointer++;
                minHeight = Math.min(minHeight, histogram[rightPointer]);
            } else {
                leftPointer--;
                minHeight = Math.min(minHeight, histogram[leftPointer]);
            }

            maxArea = Math.max(maxArea, minHeight * (rightPointer - leftPointer + 1));
        }

        while (start < leftPointer) {
            leftPointer--;
            minHeight = Math.min(minHeight, histogram[leftPointer]);
            maxArea = Math.max(maxArea, minHeight * (rightPointer - leftPointer + 1));
        }

        while (rightPointer < end) {
            rightPointer++;
            minHeight = Math.min(minHeight, histogram[rightPointer]);
            maxArea = Math.max(maxArea, minHeight * (rightPointer - leftPointer + 1));
        }

        return maxArea;
    }
}
