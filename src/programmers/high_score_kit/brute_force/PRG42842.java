package programmers.high_score_kit.brute_force;

public class PRG42842 {
    public int[] solution(int brown, int yellow) {
        int totalSize = brown + yellow;

        for (int width = 3; width <= totalSize / 3; width++) {
            if (totalSize % width == 0) {
                int height = totalSize / width;

                if ((width - 2) * (height - 2) == yellow && width >= height) {
                    return new int[] {width, height};
                }
            }
        }

        return new int[]{0, 0};
    }
}
