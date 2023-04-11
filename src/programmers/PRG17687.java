package programmers;

public class PRG17687 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; sb.length() <= t * m; i++) {
            String value = Integer.toUnsignedString(i, n);
            sb.append(value);
        }

        String s = sb.toString().toUpperCase();

        for (int j = 1; j <= t; j++) {
            answer.append(s.charAt(p - 1));
            p += m;
        }

        return answer.toString();
    }
}
