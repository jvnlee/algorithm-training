package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1541_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        List<Integer> nums = new ArrayList<>();

        while (st.hasMoreTokens()) {
            String s = st.nextToken();

            if (s.contains("+")) {
                String[] strNums = s.split("\\+");

                int sum = 0;

                for (String strNum : strNums) {
                    sum += Integer.parseInt(strNum);
                }

                nums.add(sum);
            } else {
                nums.add(Integer.parseInt(s));
            }
        }

        int answer = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            answer -= nums.get(i);
        }

        System.out.println(answer);
    }
}
