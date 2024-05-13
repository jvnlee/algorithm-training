package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        if (k == 0) {
            System.out.println(1);
            return;
        }

        int v = 1;
        int tmp = k;

        while (tmp-- > 0) {
            v *= n;
            n--;
        }

        tmp = k;

        while (tmp-- > 1) {
            k *= tmp;
        }

        System.out.println(v / k);
    }
}
