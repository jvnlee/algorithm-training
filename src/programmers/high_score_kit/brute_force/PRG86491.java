package programmers.high_score_kit.brute_force;

public class PRG86491 {
    public int solution(int[][] sizes) {
        int width = 0;
        int height = 0;

        for (int[] s : sizes) {
            width = Math.max(width, Math.max(s[0], s[1]));
            height = Math.max(height, Math.min(s[0], s[1]));
        }

        return width * height;
    }
}
