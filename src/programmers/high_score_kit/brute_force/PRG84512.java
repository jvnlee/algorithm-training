package programmers.high_score_kit.brute_force;

public class PRG84512 {
    private static int answer = 0;
    private static char[] vowels = new char[]{'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {
        buildAndCompare("", word, 5);

        return answer;
    }

    private void buildAndCompare(String current, String target, int maxLength) {
        if (current.equals(target)) {
            return;
        }

        answer++;

        if (current.length() == maxLength) {
            return;
        }

        for (char v : vowels) {
            String next = current + v;

            if (next.compareTo(target) <= 0) {
                buildAndCompare(next, target, maxLength);
            }
        }
    }
}