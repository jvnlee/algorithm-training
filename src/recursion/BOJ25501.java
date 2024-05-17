package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ25501 {
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            count = 0;
            String s = br.readLine();
            bw.write(isPalindrome(s) + " " + count + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int recursion(String s, int headPointer, int tailPointer) {
        count++;

        if (headPointer >= tailPointer) {
            return 1;
        } else if (s.charAt(headPointer) != s.charAt(tailPointer)) {
            return 0;
        } else {
            return recursion(s, headPointer + 1, tailPointer - 1);
        }
    }

    public static int isPalindrome(String s) {
        return recursion(s, 0, s.length() - 1);
    }
}
