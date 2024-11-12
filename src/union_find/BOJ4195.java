package union_find;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195 {
    private static Map<String, String> parents;
    private static Map<String, Integer> networkSizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(br.readLine());

            parents = new HashMap<>();
            networkSizes = new HashMap<>();

            StringTokenizer st;

            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());

                String n1 = st.nextToken();
                String n2 = st.nextToken();

                if (!parents.containsKey(n1)) {
                    parents.put(n1, n1);
                    networkSizes.put(n1, 1);
                }

                if (!parents.containsKey(n2)) {
                    parents.put(n2, n2);
                    networkSizes.put(n2, 1);
                }

                bw.write(union(n1, n2) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    // 두 이름의 친구 관계를 만들고 네트워크의 크기 반환
    private static int union(String n1, String n2) {
        String r1 = find(n1);
        String r2 = find(n2);

        int ns1 = networkSizes.get(r1);
        int ns2 = networkSizes.get(r2);

        if (r1.equals(r2)) { // 루트가 같다면
            return ns1; // 둘은 동일한 네트워크 안에 있으므로 둘 중 아무거나의 크기 반환
        }

        int unionSize = ns1 + ns2;

        // 큰 네트워크가 작은 네트워크를 흡수하도록 병합
        if (ns1 < ns2) {
            parents.put(r1, r2);
            networkSizes.put(r2, unionSize);
        } else {
            parents.put(r2, r1);
            networkSizes.put(r1, unionSize);
        }

        return unionSize;
    }

    private static String find(String name) {
        if (parents.get(name).equals(name)) {
            return name;
        }

        String parent = find(parents.get(name));
        parents.put(name, parent);

        return parent;
    }
}
