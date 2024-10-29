package binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] indexes = new int[N];

        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);

        for (int i = 1; i < N; i++) {
            int num = nums[i];

            if (num > lis.get(lis.size() - 1)) {
                lis.add(num);
                indexes[i] = lis.size() - 1;
            } else {
                int lo = 0;
                int hi = lis.size() - 1;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;

                    if (lis.get(mid) >= num) {
                        hi = mid;
                    } else {
                        lo = mid + 1;
                    }
                }

                lis.set(hi, num);
                indexes[i] = hi;
            }
        }

        bw.write(lis.size() + "\n");

        Stack<Integer> stack = new Stack<>();

        int curIdx = lis.size() - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (indexes[i] == curIdx) {
                stack.push(nums[i]);
                curIdx--;
            }
        }

        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }

        bw.flush();
        bw.close();
    }
}
