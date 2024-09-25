package two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 2) {
            System.out.println(0);
            return;
        }

        List<Integer> primes = generatePrimes(4_000_000);

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while (right < primes.size()) {
            if (sum > N) {
                sum -= primes.get(left);
                left++;
            } else if (sum == N) {
                count++;
                sum += primes.get(right);
                right++;
            } else {
                sum += primes.get(right);
                right++;
            }
        }

        while (sum >= N) {
            if (sum == N) {
                count++;
            }

            sum -= primes.get(left);
            left++;
        }

        System.out.println(count);
    }

    // 에라토스테네스의 체
    private static List<Integer> generatePrimes(int max) {
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false; // i의 배수들 거르기
                }
            }
        }

        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
