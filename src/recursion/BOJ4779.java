package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ4779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int n = Integer.parseInt(input);

            if (n == 0) {
                bw.write("-\n");
                continue;
            }

            String s = "-".repeat((int) Math.pow(3, n));

            bw.write(createCantorSet(s) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static String createCantorSet(String s) {
        String subStr1 = s.substring(0, s.length() / 3);
        String subStr2 = s.substring(s. length() - s.length() / 3);

        if (subStr1.length() > 1) {
            subStr1 = createCantorSet(subStr1);
            subStr2 = createCantorSet(subStr2);
        }

        String voidStr = " ".repeat(s.length() / 3);

        return subStr1 + voidStr + subStr2;
    }
}
