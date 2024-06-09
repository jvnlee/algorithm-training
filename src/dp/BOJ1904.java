package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        if (n == 1) {
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[n]);
    }

    /*
    n = 1
    1
    = 1

    n = 2
    00 / 11
    = 2

    n = 3
    001 100 / 111
    = 3

    n = 4
    0000 / 0011 1001 1100 / 1111
    = 5

    n = 5
    00001 00100 10000 / 11100 11001 10011 00111 / 11111
    = 8

    n = 6
    000000 / 000011 001100 110000 100001 100100 001001 / 111100 111001 110011 100111 001111 / 111111
    = 13

    ...

    f(n) = f(n - 1) + f(n - 2)
     */
}
