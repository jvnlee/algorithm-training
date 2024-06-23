package prefix_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int q = Integer.parseInt(br.readLine());

        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Character, int[]> map = new HashMap<>();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int[] counts;

            if (map.containsKey(a)) {
                counts = map.get(a);
            } else {
                counts = new int[s.length];

                if (s[0] == a) {
                    counts[0] = 1;
                }

                for (int j = 1; j < counts.length; j++) {
                    if (s[j] == a) {
                        counts[j] = counts[j - 1] + 1;
                    } else {
                        counts[j] = counts[j - 1];
                    }
                }

                map.put(a, counts);
            }

            int answer = counts[r] - counts[l];

            if (s[l] == a) {
                answer++;
            }

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}
