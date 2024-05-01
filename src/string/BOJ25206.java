package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        float pointGrade = 0;
        float totalPoint = 0;

        Map<String, Float> gradeMap = new HashMap<>();
        gradeMap.put("A+", 4.5f);
        gradeMap.put("A0", 4.0f);
        gradeMap.put("B+", 3.5f);
        gradeMap.put("B0", 3.0f);
        gradeMap.put("C+", 2.5f);
        gradeMap.put("C0", 2.0f);
        gradeMap.put("D+", 1.5f);
        gradeMap.put("D0", 1.0f);
        gradeMap.put("F", 0.0f);

        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            float point = Float.parseFloat(st.nextToken());
            String grade = st.nextToken();

            if (grade.equals("P")) continue;

            float gradeFloat = gradeMap.get(grade);

            pointGrade += point * gradeFloat;
            totalPoint += point;
        }

        System.out.println(pointGrade / totalPoint);
    }
}
