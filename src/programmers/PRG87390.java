package programmers;

public class PRG87390 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left) + 1];
        int idx = 0;

        for (long i = left; i < right + 1; i++) {
            answer[idx++] = Math.max((int) (i / n + 1), (int) (i % n + 1));
        }

        return answer;
    }
}
