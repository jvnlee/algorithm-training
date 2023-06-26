package programmers;

public class PRG77885 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = numbers[i] + Math.max(1L, (long) Math.pow(2, count(numbers[i]) - 1));
        }

        return answer;
    }

    public int count(long number) {
        String s = Long.toBinaryString(number);
        String reversed = new StringBuilder(s).reverse().toString();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (reversed.charAt(i) == '1') count++;
            else break;
        }

        return count;
    }
}
