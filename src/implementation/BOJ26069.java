package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        Set<String> rainbowDancers = new HashSet<>();

        rainbowDancers.add("ChongChong");

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            String b = st.nextToken();

            if (rainbowDancers.contains(a)) {
                rainbowDancers.add(b);
            } else if (rainbowDancers.contains(b)) {
                rainbowDancers.add(a);
            }
        }

        System.out.println(rainbowDancers.size());
    }
}
