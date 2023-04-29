package programmers;

public class PRG92335 {
    public int solution(int n, int k) {
        int answer = 0;
        String converted = Integer.toString(n, k);
        String[] split = converted.split("0");

        for (String s : split) {
            if (s.isEmpty()) continue;
            if (isPrime(Long.parseLong(s))) answer++;
        }

        return answer;
    }

    public boolean isPrime(long n) {
        if (n <= 1) return false;
        else if (n == 2 || n == 3) return true;
        else if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
