package programmers;

public class PRG12899 {
    public String solution(int n) {
        String[] numbers = {"4", "1", "2"};
        String answer = "";

        int tmp = n;

        while (tmp > 0) {
            int r = tmp % 3;
            tmp /= 3;

            if (r == 0) tmp--;

            answer = numbers[r] + answer;
        }

        return answer;
    }
}
