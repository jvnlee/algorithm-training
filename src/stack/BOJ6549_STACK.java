package stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// 스택 방식: O(logN)
// 분할 정복 방식: O(NlogN) (BOJ6549_DC 참조)
public class BOJ6549_STACK {
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

            bw.write(getMaxArea() + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static long getMaxArea() {
        long maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < histogram.length; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                int height = histogram[stack.pop()];
                long width = stack.isEmpty()
                        ? i
                        : (i - 1) - stack.peek();

                maxArea = Math.max(maxArea, width * height);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int height = histogram[stack.pop()];
            long width = stack.isEmpty()
                    ? histogram.length
                    : histogram.length - 1 - stack.peek();

            maxArea = Math.max(maxArea, width * height);
        }

        return maxArea;
    }
}
