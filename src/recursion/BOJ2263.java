package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2263 {
    private static int[] pre, in, post;
    private static int preIdx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        pre = new int[n];
        in = new int[n];
        post = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        getPreorder(0, n - 1, 0, n - 1);

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            answer.append(pre[i]).append(" ");
        }

        System.out.println(answer);
    }

    private static void getPreorder(int inFirst, int inLast, int postFirst, int postLast) {
        if (inLast < inFirst || postLast < postFirst) {
            return;
        }

        int root = post[postLast];
        pre[preIdx++] = root;

        int rootNodeIdx = inFirst;

        for (int i = inFirst; i <= inLast; i++) {
            if (in[i] == root) {
                rootNodeIdx = i;
                break;
            }
        }

        int leftSubTreeLength = rootNodeIdx - inFirst;

        getPreorder(inFirst, rootNodeIdx - 1, postFirst, postFirst + leftSubTreeLength - 1);
        getPreorder(rootNodeIdx + 1, inLast, postFirst + leftSubTreeLength, postLast - 1);
    }
}
