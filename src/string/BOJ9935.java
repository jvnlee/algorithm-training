package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        int strLen = str.length();
        int bombLen = bomb.length();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strLen; i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= bombLen) {
                boolean explosion = true;

                for (int j = 0; j < bombLen; j++) {
                    if (stack.get(stack.size() - bombLen + j) != bomb.charAt(j)) {
                        explosion = false;
                        break;
                    }
                }

                if (explosion) {
                    for (int j = 0; j < bombLen; j++) {
                        stack.pop(); // 폭탄 문자열의 길이만큼 스택에서 빼냄 (문자열에서 제거)
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
