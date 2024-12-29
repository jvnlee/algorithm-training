package programmers.high_score_kit.greedy;

public class PRG42860 {
    public int solution(String name) {
        int len = name.length();
        int vertical = 0;
        int horizontal = len - 1;

        for (int i = 0; i < name.length(); i++) {
            vertical += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            int nonAIdx = i + 1;

            while (nonAIdx < len && name.charAt(nonAIdx) == 'A') {
                nonAIdx++;
            }

            horizontal = Math.min(horizontal, Math.min(i, len - nonAIdx) + i + len - nonAIdx);
        }

        return vertical + horizontal;
    }
}
