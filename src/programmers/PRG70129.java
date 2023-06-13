package programmers;

public class PRG70129 {
    public int[] solution(String s) {
        int[] answer = new int[2];

        while (!s.equals("1")) {
            answer[0]++;

            for (char c : s.toCharArray()) {
                if (c == '0') answer[1]++;
            }

            s = Integer.toBinaryString(s.replace("0", "").length());
        }

        return answer;
    }
}
