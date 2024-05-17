package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        Set<String> words = new HashSet<>();
        Map<String, Integer> frequencies = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() >= m) {
                frequencies.merge(word, 1, Integer::sum);
                words.add(word);
            }
        }

        List<String> wordBook = new ArrayList<>(words);

        wordBook.sort((w1, w2) -> {
            if (frequencies.get(w1) != frequencies.get(w2)) {
                return frequencies.get(w2).compareTo(frequencies.get(w1));
            } else {
                if (w1.length() != w2.length()) {
                    return w2.length() - w1.length();
                } else {
                    return w1.compareTo(w2);
                }
            }
        });

        for (String word : wordBook) {
            bw.write(word + "\n");
        }

        bw.flush();
        bw.close();
    }
}
