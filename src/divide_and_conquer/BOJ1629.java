package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int a = Integer.parseInt(st.nextToken());
       int b = Integer.parseInt(st.nextToken());
       int c = Integer.parseInt(st.nextToken());

        System.out.println(divideAndConquer(a, b, c));
    }

    private static long divideAndConquer(int base, int exponent, int mod) {
        if (exponent == 1) {
            return base % mod;
        }

        long div = divideAndConquer(base, exponent / 2, mod);

        return exponent % 2 == 0
                ? (div * div) % mod
                : ((div * div % mod) * base) % mod;
    }

    /*
    (div * div * base) 값이 long 범위를 초과하는 경우를 고려해서 모듈러 분배법칙으로 식 변형

    (div * div * base) % mod
    = ((div * div % mod) * (base % mod)) % mod
    = (((div * div % mod) % mod) * (base % mod)) % mod
    = ((div * div % mod) * base) % mod
     */
}
