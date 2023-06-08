package programmers;

public class PRG62048 {
    public long solution(int w, int h) {
        long answer = 0;

        for (int i = 1; i < w; i++) {
            answer += (long) ((double) h * i) / w;
        }

        return answer * 2;
    }
}
