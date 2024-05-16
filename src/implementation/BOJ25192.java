package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String record = br.readLine();

            if (record.equals("ENTER")) {
                set.clear();
            } else {
                if (set.add(record)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
