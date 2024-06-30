package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11401 {
    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        /*
        페르마의 소정리 활용
             n!
        ---------- % MOD = ((n! % MOD) * ((k!(n - k)! ^ (MOD - 2)) % MOD)) % MOD
        k!(n - k)!
         */
        System.out.println(
                factorial(n) * power(
                factorial(k) * factorial(n - k) % MOD,
                MOD - 2
                )
                % MOD
        );
    }

    private static long factorial(long n) {
        long factorial = 1L;

        while (n > 1) {
            factorial = (factorial * n) % MOD;
            n--;
        }

        return factorial;
    }

    private static long power(long base, long exponent) {
        if (exponent == 1) {
            return base % MOD;
        }

        long halfExponent = power(base, exponent / 2);

        return exponent % 2 == 0
                ? halfExponent * halfExponent % MOD
                : ((halfExponent * halfExponent % MOD) * base) % MOD;
    }
}
