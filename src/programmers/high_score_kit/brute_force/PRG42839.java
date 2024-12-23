package programmers.high_score_kit.brute_force;

import java.util.*;

public class PRG42839 {
    private char[] digits;
    private boolean[] used;
    private Set<Integer> uniqueNumbers = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;

        digits = numbers.toCharArray();
        used = new boolean[digits.length];

        getUniqueNumbers("");

        for (int u : uniqueNumbers) {
            if (isPrime(u)) {
                answer++;
            }
        }

        return answer;
    }


    private void getUniqueNumbers(String current) {
        if (!current.isEmpty()) {
            uniqueNumbers.add(Integer.parseInt(current));
        }

        for (int i = 0; i < digits.length; i++) {
            if (!used[i]) {
                used[i] = true;
                getUniqueNumbers(current + digits[i]);
                used[i] = false;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
