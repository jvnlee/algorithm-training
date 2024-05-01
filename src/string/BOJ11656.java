package string;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            list.add(s.substring(i));
        }

        Collections.sort(list);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (String ss : list) {
            bw.write(ss + "\n");
        }

        bw.flush();
        bw.close();
    }
}
