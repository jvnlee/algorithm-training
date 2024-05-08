package stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Integer> stack = new Stack<>();

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = parseInt(st.nextToken());

            switch (cmd) {
                case 1:
                    stack.push(parseInt(st.nextToken()));
                    break;
                case 2:
                    if (stack.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(stack.pop() + "\n");
                    }
                    break;
                case 3:
                    bw.write(stack.size() + "\n");
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case 5:
                    if (stack.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(stack.peek() + "\n");
                    }
                    break;
            }
        }

        bw.flush();
        bw.close();
    }
}
